package andlima.hafizhfy.challengedelapan.ui.component

import andlima.hafizhfy.challengedelapan.R
import andlima.hafizhfy.challengedelapan.ui.theme.Main
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun HeaderStart() {
    Row() {
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