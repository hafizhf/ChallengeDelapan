package andlima.hafizhfy.challengedelapan.ui.component

import andlima.hafizhfy.challengedelapan.model.Coba
import andlima.hafizhfy.challengedelapan.model.CobaRepository
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun RecyclerMovie(onClick: (Coba) -> Unit) {
    val repo = CobaRepository()
    val data = repo.getCoba()

    val titleList = listOf("Let's find something to watch now.", "Popular", "New Movies")

//    var isOdd = 1
//    LazyVerticalGrid(
//        cells = GridCells.Fixed(2)
//    ) {
//        items(data) { item ->
//            if (isOdd % 2 != 0) {
//                ItemMovie(item, paddingEnd = 10.dp)
//            } else {
//                ItemMovie(item, paddingStart = 10.dp)
//            }
//            isOdd++
//        }
//    }

    var isOdd = 1
    titleList.forEach { title ->
        HeaderStickyTitle(title = title)
        LazyVerticalGrid(
            cells = GridCells.Fixed(2)
        ) {
            items(data) { item ->
                if (isOdd % 2 != 0) {
                    ItemMovie(item, paddingEnd = 10.dp) {
                        onClick(it)
                    }
                } else {
                    ItemMovie(item, paddingStart = 10.dp) {
                        onClick(it)
                    }
                }
                isOdd++
            }
        }
    }

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