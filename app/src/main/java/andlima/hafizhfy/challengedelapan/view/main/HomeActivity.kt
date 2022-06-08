package andlima.hafizhfy.challengedelapan.view.main

import andlima.hafizhfy.challengedelapan.R
import andlima.hafizhfy.challengedelapan.model.Coba
import andlima.hafizhfy.challengedelapan.model.CobaRepository
import andlima.hafizhfy.challengedelapan.ui.component.*
import andlima.hafizhfy.challengedelapan.ui.theme.*
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class HomeActivity : ComponentActivity() {
    @ExperimentalFoundationApi
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

@ExperimentalFoundationApi
@Composable
fun Home() {
    Column(
        modifier = Modifier
            .padding(25.dp, 0.dp)
//            .verticalScroll(rememberScrollState())
//            .weight(weight = 1f, fill = false)
    ) {
        HeaderHome()

//        HeaderStickyTitle(title = "AAAAAA")
        RecyclerMovie()

//        HeaderStickyTitle("Let's find something to watch now.")
        
//        Column(
//            modifier = Modifier
//                .verticalScroll(rememberScrollState())
//                .weight(weight = 1f, fill = false)
//        ) {
//        }
//        ItemMovieDummy()
    }
}

@Composable
fun HeaderStickyTitle(title: String) {
    Spacer(modifier = Modifier.padding(bottom = 20.dp))

    Row(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.width(230.dp)) {
            H1(text = title)
        }
    }

    Spacer(modifier = Modifier.padding(bottom = 7.dp))

    HR()

    Spacer(modifier = Modifier.padding(bottom = 10.dp))
}

@ExperimentalFoundationApi
@Composable
fun RecyclerMovie() {
    val repo = CobaRepository()
    val data = repo.getCoba()

    val titleList = listOf("Let's find something to watch now.", "Popular", "New Movies")

    var isOdd = 1
    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ) {
        items(data) { item ->
            if (isOdd % 2 != 0) {
                ItemMovie(item, paddingEnd = 10.dp)
            } else {
                ItemMovie(item, paddingStart = 10.dp)
            }
            isOdd++
        }
    }

//    var isOdd = 1
//    titleList.forEach { title ->
//        LazyVerticalGrid(
//            cells = GridCells.Fixed(2)
//        ) {
//            items(data) { item ->
//                if (isOdd % 2 != 0) {
//                    ItemMovie(item, paddingEnd = 10.dp)
//                } else {
//                    ItemMovie(item, paddingStart = 10.dp)
//                }
//                isOdd++
//            }
//        }
//    }

//    titleList.forEach { title ->
//        LazyColumn(
//
//        ) {
//            stickyHeader {
//                HeaderStickyTitle(title = title)
//                Log.d("JUDUL", title)
//            }
//            item {
//                var isOdd = 1
//                LazyVerticalGrid(
//                    cells = GridCells.Fixed(2),
//                    modifier = Modifier.disabledVerticalPointerInputScroll()
//                ) {
//                    items(data) { item ->
//                        if (isOdd % 2 != 0) {
//                            ItemMovie(item, paddingEnd = 10.dp)
//                        } else {
//                            ItemMovie(item, paddingStart = 10.dp)
//                        }
//                        isOdd++
//                    }
//                }
//                Log.d("JUDUL NON_STICKY", title)
//            }
//        }
//    }
}

@Composable
fun ItemMovieDummy() {
    val dim = Brush.verticalGradient(colors = listOf(DimStart, DimEnd))
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 7.dp,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.cat),
                contentDescription = "thumbnail",
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier
                .background(brush = dim)
                .matchParentSize()) {}
            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min)
                    .align(BottomStart)
                    .padding(12.dp)
            ) {
                H2(text = "Title", color = Color.White)
                H4(text = "Year release", color = Color.White)
            }
        }
    }
}

@Composable
fun ItemMovie(coba: Coba, paddingStart: Dp? = null, paddingEnd: Dp? = null) {
    val dim = Brush.verticalGradient(colors = listOf(DimStart, DimEnd))
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 7.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp)
            .padding(start = paddingStart ?: 0.dp)
            .padding(end = paddingEnd ?: 0.dp)
    ) {
        Box(
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.cat),
                contentDescription = "thumbnail",
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier
                .background(brush = dim)
                .matchParentSize()) {}
            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min)
                    .align(BottomStart)
                    .padding(12.dp)
            ) {
                H2(text = coba.title, color = Color.White)
                H4(text = coba.yearRelease, color = Color.White)
            }
        }
    }
}

@Composable
fun HR(width: Dp? = null, height: Dp? = null) {
    Card(
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Main,
        elevation = 0.dp,
        modifier = Modifier
            .width(width ?: 50.dp)
            .height(height ?: 3.dp)
            .shadow(0.dp)
    ) {}
}

@Composable
fun HeaderHome() {
    Row(modifier = Modifier.padding(top = 25.dp)) {
        Column {
            H4(text = "Welcome,")
            H2(text = "User", color = Main)
        }
        Spacer(modifier = Modifier.weight(1.0F))

        Icon(Icons.Rounded.Search, contentDescription = "Search")
        Spacer(modifier = Modifier.padding(start = 5.dp))
        Icon(Icons.Rounded.FavoriteBorder, contentDescription = "Favorite")
        Spacer(modifier = Modifier.padding(start = 5.dp))
        Icon(Icons.Rounded.Person, contentDescription = "User")
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