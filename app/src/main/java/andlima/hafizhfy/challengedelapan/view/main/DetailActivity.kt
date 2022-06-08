package andlima.hafizhfy.challengedelapan.view.main

import andlima.hafizhfy.challengedelapan.R
import andlima.hafizhfy.challengedelapan.model.Coba
import andlima.hafizhfy.challengedelapan.ui.component.*
import andlima.hafizhfy.challengedelapan.ui.theme.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import kotlin.math.max

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val getIntent = intent?.getBundleExtra("DATA")
        val selectedData = getIntent?.getParcelable<Coba>("SELECTED_DATA") as Coba

        setContent {
            ChallengeDelapanTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Detail(selectedData)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Detail(coba: Coba) {
    Column {
        HeaderDetailCollapsingToolbar(coba)
    }
}

@ExperimentalFoundationApi
@Composable
fun HeaderDetailCollapsingToolbar(coba: Coba) {
    val scrollState = rememberLazyListState()
    val scrollOffset: Float = minOf(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset/400f)
                + scrollState.firstVisibleItemIndex
    )

    Column {
        // max(after, before)
        val imageHeight by animateDpAsState(targetValue = max(120.dp, 500.dp*scrollOffset))
        val cobaAlpha by animateFloatAsState(targetValue = max(0.0F, 1.0F*scrollOffset))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
        ) {
            Image(
                painter = painterResource(R.drawable.cat),
                contentDescription = "thumbnail",
                contentScale = ContentScale.Crop
            )
            Row(modifier = Modifier.align(Alignment.BottomStart)) {
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .height(IntrinsicSize.Min)
                        .padding(20.dp)
                ) {
                    H2(text = coba.title, color = Color.White)
                    H4(text = coba.yearRelease, color = Color.White)
                }

                Spacer(modifier = Modifier.weight(1.0F))

                Card(
                    backgroundColor = Main,
                    modifier = Modifier
                        .width(100.dp)
                        .height(200.dp)
                        .alpha(cobaAlpha)
                ) {}
            }
        }
    }

    LazyColumn(
        state = scrollState
    ) {
        item {
            Column(Modifier.padding(20.dp)) {
                Spacer(modifier = Modifier.padding(bottom = 200.dp))
                Body1(text = "Aasdgsagdasdg sadgasdgasd gasdga sdgx zcbvxc b sfedb dsfgb asdf awefasdg b")
                Spacer(modifier = Modifier.padding(bottom = 700.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview4() {
    ChallengeDelapanTheme {
        Detail(Coba("AAA", "2077"))
    }
}