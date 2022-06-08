package andlima.hafizhfy.challengedelapan.ui.component

import andlima.hafizhfy.challengedelapan.R
import andlima.hafizhfy.challengedelapan.model.Coba
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
fun ItemMovie(coba: Coba, paddingStart: Dp? = null, paddingEnd: Dp? = null, onClick: (Coba) -> Unit) {
    val dim = Brush.verticalGradient(colors = listOf(DimStart, DimEnd))
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 7.dp,
        onClick = {onClick(coba)},
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
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                H2(text = coba.title, color = Color.White)
                H4(text = coba.yearRelease, color = Color.White)
            }
        }
    }
}