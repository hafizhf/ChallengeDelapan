package andlima.hafizhfy.challengedelapan.view.main

import andlima.hafizhfy.challengedelapan.R
import andlima.hafizhfy.challengedelapan.func.toast
import andlima.hafizhfy.challengedelapan.model.film.GetMockFilmResponse
import andlima.hafizhfy.challengedelapan.repository.DummyRepository
import andlima.hafizhfy.challengedelapan.ui.component.*
import andlima.hafizhfy.challengedelapan.ui.theme.*
import andlima.hafizhfy.challengedelapan.viewmodel.HomeViewModel
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.max

@AndroidEntryPoint
class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        transparentStatusBar()

        val getIntent = intent?.getBundleExtra("DATA")
        val selectedData = getIntent?.getParcelable<GetMockFilmResponse>("SELECTED_DATA") as GetMockFilmResponse

        setContent {
            ChallengeDelapanTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val moviesViewModel = viewModel(modelClass = HomeViewModel::class.java)
                    val moviesData by moviesViewModel.filmState.collectAsState()

                    Detail(selectedData, moviesData)
                }
            }
        }
    }

    private fun transparentStatusBar() {
        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        window.statusBarColor = Color.Transparent.hashCode()
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Detail(movie: GetMockFilmResponse, movies: List<GetMockFilmResponse>) {
    Column {
        HeaderDetailCollapsingToolbar(movie, movies)
    }
}

@ExperimentalFoundationApi
@Composable
fun HeaderDetailCollapsingToolbar(movie: GetMockFilmResponse, movies: List<GetMockFilmResponse>) {
    val scrollState = rememberLazyListState()
    val scrollOffset: Float = minOf(
        1f,
        1 - (scrollState.firstVisibleItemScrollOffset/400f)
                + scrollState.firstVisibleItemIndex
    )

    Column {
        // max(after, before)
        val imageHeight by animateDpAsState(targetValue = max(140.dp, 600.dp*scrollOffset))
        val buttonAlpha by animateFloatAsState(targetValue = max(0.0F, 1.0F*scrollOffset))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
        ) {
            Image(
//                painter = painterResource(R.drawable.cat),
                painter = rememberImagePainter(data = movie.image),
                contentDescription = "thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Box(modifier = Modifier
                .background(brush = Dim)
                .matchParentSize()) {}
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            ) {
                Title(movies = movie, buttonAlpha = buttonAlpha)
            }
        }
    }
    Description(scrollState, movie, movies)
}

@Composable
private fun Title(movies: GetMockFilmResponse, buttonAlpha: Float) {
    val context = LocalContext.current
    Row(verticalAlignment = Alignment.Bottom) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .width(160.dp)
        ) {
            H1(text = movies.title, color = Color.White)
            H4(text = movies.releaseDate, color = Color.White)
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Description(scrollState: LazyListState, movie: GetMockFilmResponse, movies: List<GetMockFilmResponse>) {
    val context = LocalContext.current
    LazyColumn(
        state = scrollState
    ) {
        item {
            Column(modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.padding(bottom = 140.dp))
                Column(Modifier.padding(20.dp)) {
                    Row(Modifier.fillMaxWidth()) {
                        H3(text = "Director: ")
                        Spacer(modifier = Modifier.padding(bottom = 5.dp))
                        Body1(text = movie.director)
                    }
                    H3(text = "Synopsys: ")
                    Spacer(modifier = Modifier.padding(bottom = 7.dp))
                    Body1(text = movie.synopsis)
                }

                SimilarMovies(movies) {
                    val detail = Intent(context, DetailActivity::class.java)
                    val bundle = Bundle()
                    bundle.putParcelable("SELECTED_DATA", it)
                    detail.putExtra("DATA", bundle)
                    context.startActivity(detail)
                }
                Spacer(modifier = Modifier.padding(bottom = 160.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview4() {
    ChallengeDelapanTheme {
//        Detail(Coba("AAA", "2077"))
        Detail(
            GetMockFilmResponse("Today", "Me", "1", "", "2077", "It's a film!", "Spyder-Man: No Way Home"),
            DummyRepository().getDummyMovie()
        )
    }
}