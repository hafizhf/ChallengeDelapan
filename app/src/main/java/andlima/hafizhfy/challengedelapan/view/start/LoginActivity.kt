package andlima.hafizhfy.challengedelapan.view.start

import andlima.hafizhfy.challengedelapan.func.toast
import andlima.hafizhfy.challengedelapan.ui.component.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.tooling.preview.Preview
import andlima.hafizhfy.challengedelapan.ui.theme.ChallengeDelapanTheme
import andlima.hafizhfy.challengedelapan.view.main.HomeActivity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class LoginActivity : ComponentActivity() {

    // Used for double back to exit app
    private var doubleBackToExit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeDelapanTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Login()
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
private fun Login() {
    val context = LocalContext.current

    Column(modifier = Modifier.padding(25.dp)) {
        HeaderStart()

        Spacer(modifier = Modifier.weight(1.0F))
        
        H1(text = "Login")

        var isEmailError by rememberSaveable { mutableStateOf(false) }
        var email by remember { mutableStateOf("") }
        email = InputFieldEmail("Email", isEmailError)

        var isPasswordError by rememberSaveable { mutableStateOf(false) }
        var password by remember { mutableStateOf("") }
        password = InputFieldPassword("Password" ,isPasswordError, "Wrong password")

        Spacer(modifier = Modifier.weight(1.0F))
        Spacer(modifier = Modifier.weight(0.3F))

        ButtonMain("Login") {
            isPasswordError = password != "asd"
            isEmailError = email != "admin@gmail.com"

            if (!isEmailError && !isPasswordError) {
                val gotoLogin = Intent(context, HomeActivity::class.java)
                gotoLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK) // since idk how to finish(), hopefully this is good
                context.startActivity(gotoLogin)
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DefaultPreview() {
    ChallengeDelapanTheme {
        Login()
    }
}