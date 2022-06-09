package andlima.hafizhfy.challengedelapan.ui.component

import andlima.hafizhfy.challengedelapan.ui.theme.DimEnd
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressLoading(loadingState: Boolean, message: String) {
    AnimatedVisibility(
        visible = loadingState,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .background(DimEnd)
                    .matchParentSize()) {}
            Column(
                modifier = Modifier.matchParentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(60.dp))
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                H2(text = message, color = Color.White)
            }
        }
    }
}