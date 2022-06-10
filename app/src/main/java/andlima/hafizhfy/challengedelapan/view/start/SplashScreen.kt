package andlima.hafizhfy.challengedelapan.view.start

import andlima.hafizhfy.challengedelapan.R
import andlima.hafizhfy.challengedelapan.ui.component.H1
import andlima.hafizhfy.challengedelapan.ui.theme.ChallengeDelapanTheme
import andlima.hafizhfy.challengedelapan.ui.theme.Main
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@SuppressLint("CustomSplashScreen")
class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeDelapanTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Splash()
                }
            }
        }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}

@Composable
fun Splash() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
       Column(
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier
               .fillMaxSize()
               .background(color = Main)
       ) {
           Image(
               painter = painterResource(id = R.drawable.ic_logo_white),
               contentDescription = "logo",
               modifier = Modifier.size(120.dp)
           )
           Spacer(modifier = Modifier.height(15.dp))
           H1(text = "Moovie Compose", color = Color.White, size = 28.sp)
       }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview5() {
    ChallengeDelapanTheme {
        Splash()
    }
}