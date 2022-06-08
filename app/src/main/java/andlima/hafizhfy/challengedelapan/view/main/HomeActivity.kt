package andlima.hafizhfy.challengedelapan.view.main

import andlima.hafizhfy.challengedelapan.ui.component.*
import andlima.hafizhfy.challengedelapan.ui.theme.*
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeDelapanTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Home()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalFoundationApi
@Composable
fun Home() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(25.dp, 0.dp)
//            .verticalScroll(rememberScrollState())
//            .weight(weight = 1f, fill = false)
    ) {
        HeaderHome()

        RecyclerMovie() {
            val detail = Intent(context, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("SELECTED_DATA", it)
            detail.putExtra("DATA", bundle)
            context.startActivity(detail)
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview3() {
    ChallengeDelapanTheme {
        Home()
    }
}