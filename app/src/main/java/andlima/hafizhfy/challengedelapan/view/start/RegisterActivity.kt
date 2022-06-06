package andlima.hafizhfy.challengedelapan.view.start

import andlima.hafizhfy.challengedelapan.ui.component.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.tooling.preview.Preview
import andlima.hafizhfy.challengedelapan.ui.theme.ChallengeDelapanTheme
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.compose.runtime.saveable.rememberSaveable


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

@Composable
private fun Register() {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

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

            when {
                username == "" -> {
                    usernameError = true
                }
                email == "admin@gmail.com" -> {
                    emailError = true
                }
                password.length < 8 -> {
                    passwordError = true
                }
                rePassword != password -> {
                    rePasswordError = true
                }
                !usernameError && !emailError && !passwordError && !rePasswordError -> {
                    coroutineScope.launch {
                        val snack = scaffoldState.snackbarHostState.showSnackbar(
                            "Register success"
                        )
                        when (snack) {
                            SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
                            SnackbarResult.ActionPerformed -> Log.d("SnackbarDemo", "Snackbar button clicked")
                        }
                    }

                    val gotoLogin = Intent(context, LoginActivity::class.java)
                    gotoLogin.addFlags(FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK) // since idk how to finish(), hopefully this is good
                    context.startActivity(gotoLogin)
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DefaultPreview2() {
    ChallengeDelapanTheme {
        Register()
    }
}