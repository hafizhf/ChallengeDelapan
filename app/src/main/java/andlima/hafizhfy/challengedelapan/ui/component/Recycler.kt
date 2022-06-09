package andlima.hafizhfy.challengedelapan.ui.component

import andlima.hafizhfy.challengedelapan.model.film.GetMockFilmResponse
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun RecyclerMovie(movies: List<GetMockFilmResponse>, scrollState: LazyListState, onClick: (GetMockFilmResponse) -> Unit) {
    val titleList = listOf("Let's find something to watch now.", "Popular", "New Movies")

    var isOdd = 1
    titleList.forEach { title ->
        HeaderStickyTitle(title = title)
        LazyVerticalGrid(
            state = scrollState,
            cells = GridCells.Fixed(2)
        ) {
            items(movies) { item ->
                if (isOdd % 2 != 0) {
                    ItemMovie(item, paddingEnd = 10.dp) {
                        onClick(it)
                    }
                } else {
                    ItemMovie(item, paddingStart = 10.dp) {
                        onClick(it)
                    }
                }
                if (isOdd == titleList.size-1) {
                    isOdd = 1
                } else {
                    isOdd++
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SimilarMovies(movies: List<GetMockFilmResponse>, onClick: (GetMockFilmResponse) -> Unit) {

    HeaderStickyTitle("Similar movies", 20.dp)
    LazyRow {
        item {
            Spacer(modifier = Modifier.width(20.dp))
        }
        items(movies) { movie ->
            ItemMovieHorizontal(
                movies = movie,
                paddingEnd = 20.dp
            ) {
                onClick(it)
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun ExperimentalRecyclerMovie(movies: List<GetMockFilmResponse>, scrollState: LazyListState, onClick: (GetMockFilmResponse) -> Unit) {
//    val repo = CobaRepository()
//    val data = repo.getCoba()

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


//    titleList.forEach { title ->
//        LazyColumn(
//
//        ) {
//            stickyHeader {
//                HeaderStickyTitle(title = title)
//                Log.d("Title", title)
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
//                Log.d("Title NON_STICKY", title)
//            }
//        }
//    }
}