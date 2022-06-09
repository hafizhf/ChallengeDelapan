package andlima.hafizhfy.challengedelapan.ui.component

import andlima.hafizhfy.challengedelapan.R
import andlima.hafizhfy.challengedelapan.ui.theme.Main
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HeaderStart() {
    Row {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 5.dp)
                .size(30.dp)
        )
        Text(
            text = "Moovie Compose",
            style = MaterialTheme.typography.h4,
            color = Main,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(top = 1.dp)
        )
    }
}

@Composable
fun HeaderStickyTitle(title: String, paddingStart: Dp? = null) {
    Spacer(modifier = Modifier.padding(bottom = 20.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = paddingStart ?: 0.dp)
    ) {
        Row(modifier = Modifier.width(230.dp)) {
            H1(text = title)
        }
    }

    Spacer(modifier = Modifier.padding(bottom = 7.dp))

    HR(paddingStart = paddingStart ?: 0.dp)

    Spacer(modifier = Modifier.padding(bottom = 10.dp))
}

@Composable
fun HeaderHome(modifier: Modifier? = Modifier, username: String? = null, paddingTop: Dp? = null, onClick: (Any) -> Unit) {
    Row(
        modifier = Modifier
            .padding(top = paddingTop ?: 25.dp)
    ) {
        Row(modifier = modifier!!) {
            Column {
                H4(text = "Welcome,")
                H2(
                    text = username ?: "User",
                    color = Main
                )
            }
            Spacer(modifier = Modifier.weight(1.0F))

            Icon(Icons.Rounded.Search, contentDescription = "Search")
            Spacer(modifier = Modifier.padding(start = 5.dp))
            Icon(Icons.Rounded.FavoriteBorder, contentDescription = "Favorite")
            Spacer(modifier = Modifier.padding(start = 5.dp))
            Icon(
                Icons.Rounded.Person,
                contentDescription = "User",
                Modifier.clickable {
                    onClick(true)
                }
            )
        }
    }
}

@Composable
fun HR(width: Dp? = null, height: Dp? = null, paddingStart: Dp? = null) {
    Card(
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Main,
        elevation = 0.dp,
        modifier = Modifier
            .width(width ?: 50.dp)
            .height(height ?: 3.dp)
            .shadow(0.dp)
            .padding(start = paddingStart ?: 0.dp)
    ) {}
}

//private val VerticalScrollConsumer = object : NestedScrollConnection {
//    override fun onPreScroll(available: Offset, source: NestedScrollSource) = available.copy(x = 0f)
//    override suspend fun onPreFling(available: Velocity) = available.copy(x = 0f)
//}
//
//private val HorizontalScrollConsumer = object : NestedScrollConnection {
//    override fun onPreScroll(available: Offset, source: NestedScrollSource) = available.copy(y = 0f)
//    override suspend fun onPreFling(available: Velocity) = available.copy(y = 0f)
//}
//
//fun Modifier.disabledVerticalPointerInputScroll(disabled: Boolean = true) =
//    if (disabled) this.nestedScroll(VerticalScrollConsumer) else this
//
//fun Modifier.disabledHorizontalPointerInputScroll(disabled: Boolean = true) =
//    if (disabled) this.nestedScroll(HorizontalScrollConsumer) else this