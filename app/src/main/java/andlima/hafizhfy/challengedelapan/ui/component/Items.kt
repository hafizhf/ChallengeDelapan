package andlima.hafizhfy.challengedelapan.ui.component

import andlima.hafizhfy.challengedelapan.R
import andlima.hafizhfy.challengedelapan.model.film.GetMockFilmResponse
import andlima.hafizhfy.challengedelapan.ui.theme.Dim
import andlima.hafizhfy.challengedelapan.ui.theme.DimEnd
import andlima.hafizhfy.challengedelapan.ui.theme.DimStart
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

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
                .fillMaxWidth()
//                .width(IntrinsicSize.Min)
                .height(220.dp)
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
//                    .width(IntrinsicSize.Min)
                    .width(200.dp)
                    .height(IntrinsicSize.Min)
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                H2(text = "Title", color = Color.White)
                H4(text = "Year release", color = Color.White)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ItemMovie(movies: GetMockFilmResponse, paddingStart: Dp? = null, paddingEnd: Dp? = null, onClick: (GetMockFilmResponse) -> Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 7.dp,
        onClick = {onClick(movies)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp)
            .padding(start = paddingStart ?: 0.dp)
            .padding(end = paddingEnd ?: 0.dp)
    ) {
        Box(
            modifier = Modifier
//                .width(IntrinsicSize.Min)
                .fillMaxWidth()
                .height(210.dp)
        ) {
            Image(
//                painter = painterResource(R.drawable.cat),
                painter = rememberImagePainter(data = movies.image),
                contentDescription = "thumbnail",
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier
                .background(brush = Dim)
                .matchParentSize()) {}
            Column(
                modifier = Modifier
//                    .width(IntrinsicSize.Min)
                    .width(150.dp)
                    .height(IntrinsicSize.Min)
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                H2(text = movies.title, color = Color.White)
                H4(text = movies.releaseDate, color = Color.White)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ItemMovieHorizontal(movies: GetMockFilmResponse, paddingStart: Dp? = null, paddingEnd: Dp? = null, onClick: (GetMockFilmResponse) -> Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 7.dp,
        onClick = {onClick(movies)},
        modifier = Modifier
            .width(150.dp)
            .padding(0.dp, 10.dp)
            .padding(start = paddingStart ?: 0.dp)
            .padding(end = paddingEnd ?: 0.dp)
    ) {
        Box(
            modifier = Modifier
//                .width(IntrinsicSize.Min)
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Image(
//                painter = painterResource(R.drawable.cat),
                painter = rememberImagePainter(data = movies.image),
                contentDescription = "thumbnail",
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier
                .background(brush = Dim)
                .matchParentSize()) {}
            Column(
                modifier = Modifier
//                    .width(IntrinsicSize.Min)
                    .width(140.dp)
                    .height(IntrinsicSize.Min)
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                H2(text = movies.title, color = Color.White)
                H4(text = movies.releaseDate, color = Color.White)
            }
        }
    }
}