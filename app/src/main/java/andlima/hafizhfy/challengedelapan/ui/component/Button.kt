package andlima.hafizhfy.challengedelapan.ui.component

import andlima.hafizhfy.challengedelapan.ui.theme.Main
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonMain(text: String, onClickAction: (Any) -> Unit) {
    Button(
        onClick = { onClickAction(true) },
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(Main),
        modifier = Modifier
            .fillMaxWidth()
//            .background(Color(R.color.main))
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h3,
            color = Color.White
        )
    }
}