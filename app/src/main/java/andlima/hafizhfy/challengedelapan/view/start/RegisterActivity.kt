package andlima.hafizhfy.challengedelapan.view.start

import andlima.hafizhfy.challengedelapan.di.UserClient
import andlima.hafizhfy.challengedelapan.func.toast
import andlima.hafizhfy.challengedelapan.model.user.GetUserItem
import andlima.hafizhfy.challengedelapan.model.user.RequestUser
import andlima.hafizhfy.challengedelapan.ui.component.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.tooling.preview.Preview
import andlima.hafizhfy.challengedelapan.ui.theme.ChallengeDelapanTheme
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response

@DelicateCoroutinesApi
class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeDelapanTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Register()
                }
            }
        }
    }
}

@DelicateCoroutinesApi
@Composable
private fun Register() {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
        Column(modifier = Modifier.padding(25.dp)) {
            HeaderStart()

            Spacer(modifier = Modifier.weight(0.7F))

            H1(text = "Register")

            var username by remember { mutableStateOf("") }
            var usernameError by rememberSaveable { mutableStateOf(false) }
            username = InputField("Username", true, usernameError)

            var email by remember { mutableStateOf("") }
            var emailError by rememberSaveable { mutableStateOf(false) }
            email = InputField("Email", true, emailError, "Email already registered")

            var password by remember { mutableStateOf("") }
            var passwordError by rememberSaveable { mutableStateOf(false) }
            password = InputFieldPassword("Password", passwordError, "Password at least 8 characters")

            var rePassword by remember { mutableStateOf("") }
            var rePasswordError by rememberSaveable { mutableStateOf(false) }
            rePassword = InputFieldPassword("Re-enter Password", rePasswordError, "Password not match")

            Spacer(modifier = Modifier.weight(1.0F))
            Spacer(modifier = Modifier.weight(0.3F))

            ButtonMain("Register") {
                usernameError = username == ""
                emailError = email == ""
                passwordError = password == ""
                rePasswordError = rePassword == ""

                if (username != "" && email != "" && password != "" && rePassword != "") {
                    isLoading = true

                    GlobalScope.launch {
                        registerAuth(context, username, email, password, rePassword) { emailResult, passwordResult, rePasswordResult ->
                            emailError = emailResult
                            passwordError = passwordResult
                            rePasswordError = rePasswordResult

                            if (!usernameError && !emailError && !passwordError && !rePasswordError) {
                                isLoading = false
                                val gotoLogin = Intent(context, LoginActivity::class.java)
                                gotoLogin.addFlags(FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK) // since idk how to finish(), hopefully this is good
                                context.startActivity(gotoLogin)
                            } else {
                                isLoading = false
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(bottom = 5.dp))

            Row(modifier = Modifier.align(CenterHorizontally)) {
                Body1(text = "You have account? ")
                Body1Clickable("Login") {
                    val gotoLogin = Intent(context, LoginActivity::class.java)
                    gotoLogin.addFlags(FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK) // since idk how to finish(), hopefully this is good
                    context.startActivity(gotoLogin)
                }
            }
        }
        ProgressLoading(loadingState = isLoading, message = "Registering")
    }
}

@DelicateCoroutinesApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DefaultPreview2() {
    ChallengeDelapanTheme {
        Register()
    }
}

// END OF UI CODE ===============================================================================###

@DelicateCoroutinesApi
private suspend fun registerAuth(
    context: Context,
    username: String,
    email: String,
    password: String,
    rePassword: String,
    validationError: (
        emailError: Boolean,
        passwordError: Boolean,
        rePasswordError: Boolean
    ) -> Unit
) {
    val validationResult = GlobalScope.async { emailValidation(email) }
    val emailFound = validationResult.await()

    when {
        emailFound < 0 || emailFound > 0 -> {
            validationError(true, false, false)
        }
        password.length < 8 -> {
            validationError(false, true, false)
        }
        rePassword != password -> {
            validationError(false, false, true)
        }
        else -> {
            validationError(false, false, false)
            register(context, username, email, password)
        }
    }
}

private suspend fun emailValidation(email: String): Int {
    // >= 0 mean response successful
    // < 0 mean response error
    // default is 0
    var result = 0

    UserClient.instance.getUser(email)
        .enqueue(object : retrofit2.Callback<List<GetUserItem>>{
            override fun onResponse(
                call: Call<List<GetUserItem>>,
                response: Response<List<GetUserItem>>
            ) {
                result = if (response.isSuccessful) {
                    response.body()!!.size
                } else {
                    -1
                }
            }
            override fun onFailure(call: Call<List<GetUserItem>>, t: Throwable) {
                result = -2
            }
        })
    delay(2000)
    return result
}

private fun register(
    context: Context,
    username: String,
    email: String,
    password: String
) {
    UserClient.instance.postUser(RequestUser(email, password, username, ""))
        .enqueue(object : retrofit2.Callback<GetUserItem>{
            override fun onResponse(call: Call<GetUserItem>, response: Response<GetUserItem>) {
                if (response.isSuccessful) {
                    toast(context, "Register success")
                } else {
                    toast(context, "Register failed\n" + response.message())
                }
            }

            override fun onFailure(call: Call<GetUserItem>, t: Throwable) {
                toast(context, "Register error\nNo connection")
            }
        })
}