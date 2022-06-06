package andlima.hafizhfy.challengedelapan.ui.component

import andlima.hafizhfy.challengedelapan.ui.theme.Main
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun H1(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h1
    )
}

@Composable
fun Body1(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun ErrorText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.error,
        fontSize = 14.sp,
        modifier = Modifier.padding(start = 15.dp)
    )
}

@Composable
fun Body1Clickable(text: String, onClickAction: (Any) -> Unit) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        color = Main,
        modifier = Modifier.clickable { onClickAction(true) }
    )
}