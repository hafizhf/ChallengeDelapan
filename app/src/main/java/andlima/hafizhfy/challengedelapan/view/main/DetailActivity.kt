package andlima.hafizhfy.challengedelapan.view.main

import andlima.hafizhfy.challengedelapan.R
import andlima.hafizhfy.challengedelapan.func.toast
import andlima.hafizhfy.challengedelapan.model.Coba
import andlima.hafizhfy.challengedelapan.ui.component.*
import andlima.hafizhfy.challengedelapan.ui.theme.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.platform.LocalContext
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
        val imageHeight by animateDpAsState(targetValue = max(120.dp, 600.dp*scrollOffset))
        val buttonAlpha by animateFloatAsState(targetValue = max(0.0F, 1.0F*scrollOffset))

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
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            ) {
                Title(coba = coba, buttonAlpha = buttonAlpha)
            }
        }
    }
    Description(scrollState)
}

@Composable
private fun Title(coba: Coba, buttonAlpha: Float) {
    val context = LocalContext.current
    Row(verticalAlignment = Alignment.Bottom) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .width(150.dp)
        ) {
            H2(text = coba.title, color = Color.White)
            H4(text = coba.yearRelease, color = Color.White)
        }

        Spacer(modifier = Modifier.weight(1.0F))

        Card(
            border = BorderStroke(1.dp, Color.White),
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            shape = RoundedCornerShape(100),
            modifier = Modifier
                .shadow(0.dp)
                .alpha(buttonAlpha)
                .clickable(enabled = buttonAlpha >= 0.3F) {
                    toast(context, "Button clicked!")
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp)
                    .padding(end = 8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_play),
                    contentDescription = "Play",
                    modifier = Modifier.padding(end = 3.dp)
                )
                H4(text = "Watch now", color = Color.White)
            }
        }
    }
}

@Composable
private fun Description(scrollState: LazyListState) {
    LazyColumn(
        state = scrollState
    ) {
        item {
            Column(Modifier.padding(20.dp)) {
                Spacer(modifier = Modifier.padding(bottom = 180.dp))
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