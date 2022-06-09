package andlima.hafizhfy.challengedelapan.view.start

import andlima.hafizhfy.challengedelapan.di.UserClient
import andlima.hafizhfy.challengedelapan.func.toast
import andlima.hafizhfy.challengedelapan.local.datastore.UserManager
import andlima.hafizhfy.challengedelapan.model.user.GetUserItem
import andlima.hafizhfy.challengedelapan.ui.component.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.tooling.preview.Preview
import andlima.hafizhfy.challengedelapan.ui.theme.ChallengeDelapanTheme
import andlima.hafizhfy.challengedelapan.ui.theme.Dim
import andlima.hafizhfy.challengedelapan.ui.theme.DimEnd
import andlima.hafizhfy.challengedelapan.ui.theme.Main
import andlima.hafizhfy.challengedelapan.view.main.HomeActivity
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.OnBackPressedCallback
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class LoginActivity : ComponentActivity() {

    // Get data store
    lateinit var userManager: UserManager

    // Used for double back to exit app
    private var doubleBackToExit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userManager = UserManager(this)

        isUserLoggedIn(userManager, this) {
            toast(this, "Welcome back")
            startActivity(Intent(this, HomeActivity::class.java))
            overridePendingTransition(0,0)
            finish()
        }

        setContent {
            ChallengeDelapanTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Login(userManager)
                }
            }
        }

        // Check if user click back button twice
        doubleBackExit()
    }

    // Function to exit app with double click on back button----------------------------------------
    private fun doubleBackExit() {
        onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    if (doubleBackToExit) {
                        finish()
                    } else {
                        doubleBackToExit = true
                        toast(this@LoginActivity, "Press again to exit")

                        Handler(Looper.getMainLooper()).postDelayed(Runnable {
                            kotlin.run {
                                doubleBackToExit = false
                            }
                        }, 2000)
                    }
                }
            })
    }
}

@Composable
private fun Login(userManager: UserManager) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
        Column(modifier = Modifier.padding(25.dp)) {
            HeaderStart()

            Spacer(modifier = Modifier.weight(1.0F))

            H1(text = "Login")

            var isEmailError by rememberSaveable { mutableStateOf(false) }
            var email by remember { mutableStateOf("") }
            email = InputFieldEmail("Email", isEmailError)

            var isPasswordError by rememberSaveable { mutableStateOf(false) }
            var password by remember { mutableStateOf("") }
            password = InputFieldPassword("Password", isPasswordError, "Wrong password")

            Spacer(modifier = Modifier.weight(1.0F))
            Spacer(modifier = Modifier.weight(0.3F))

            ButtonMain("Login") {
                isEmailError = email == ""
                isPasswordError = password == ""

                if (email != "" && password != "") {
                    isLoading = true
                    loginAuth(context, email, password, userManager) { emailResult, passwordResult ->
                        isEmailError = emailResult
                        isPasswordError = passwordResult

                        if (!isEmailError && !isPasswordError) {
                            isLoading = false
                            toast(context, "Welcome back")
                            val gotoHome = Intent(context, HomeActivity::class.java)
                            gotoHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK) // since idk how to finish(), hopefully this is good
                            context.startActivity(gotoHome)
                        } else {
                            isLoading = false
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(bottom = 5.dp))

            Row(modifier = Modifier.align(CenterHorizontally)) {
                Body1(text = "Don't have account? ")
                Body1Clickable("Register") {
                    context.startActivity(Intent(context, RegisterActivity::class.java))
                }
            }
        }
        ProgressLoading(loadingState = isLoading, message = "Logging In")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DefaultPreview() {
    ChallengeDelapanTheme {
        val context = LocalContext.current
//        Login() // No preview due to requirement an activity for the function
        Login(UserManager(context))
    }
}

// END OF UI CODE ===============================================================================###

private fun loginAuth(
    context: Context, 
    email: String, 
    password: String, 
    userManager: UserManager, 
    validationError: (emailError: Boolean, passwordError: Boolean) -> Unit
) {
    UserClient.instance.getUser(email)
        .enqueue(object : retrofit2.Callback<List<GetUserItem>>{
            override fun onResponse(
                call: Call<List<GetUserItem>>,
                response: Response<List<GetUserItem>>
            ) {
                if (response.isSuccessful) {
                    when {
                        response.body()?.isEmpty() == true -> {
                            validationError(true, true)
                            toast(context, "Unknown user, please register")
                        }
                        response.body()?.size!! > 1 -> {
                            validationError(true, true)
                            toast(context, "Please input data correctly")
                        }
                        email != response.body()!![0].email -> {
                            validationError(true, false)
                        }
                        password != response.body()!![0].password -> {
                            validationError(false, true)
                        }
                        else -> {
                            validationError(false, false)
                            login(userManager, response.body()!![0])
                        }
                    }
                } else {
                    validationError(true, true)
                    toast(context, "Login failed\nNo response")
                }
            }

            override fun onFailure(call: Call<List<GetUserItem>>, t: Throwable) {
                validationError(true, true)
                toast(context, "Login error\nNo connection")
            }
        })
}

private fun login(
    userManager: UserManager,
    userData: GetUserItem
) {
    GlobalScope.launch {
        userManager.loginUserData(
            userData.username,
            userData.email,
            userData.avatar,
            userData.password,
            userData.id,
            userData.complete_name,
            userData.address,
            userData.dateofbirth
        )
    }
}

private fun isUserLoggedIn(userManager: UserManager, lifecycleOwner: LifecycleOwner, action: (Any) -> Unit) {
    userManager.email.asLiveData().observe(lifecycleOwner, { email ->
        userManager.password.asLiveData().observe(lifecycleOwner, { password ->

            if (email != "" && password != "") {
                action(true)
            }
        })
    })
}