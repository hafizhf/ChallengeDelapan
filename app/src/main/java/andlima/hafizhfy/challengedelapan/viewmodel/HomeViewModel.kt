package andlima.hafizhfy.challengedelapan.viewmodel

import andlima.hafizhfy.challengedelapan.model.film.GetMockFilmResponse
import andlima.hafizhfy.challengedelapan.model.film.GetMovies
import andlima.hafizhfy.challengedelapan.model.film.GetMoviesResponse
import andlima.hafizhfy.challengedelapan.repository.MoviesRepository
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(api: MoviesRepository) : ViewModel()  {

    val apiRepository = api

    // MOCK API ====================================================================================
    private val filmMutableState = MutableStateFlow(emptyList<GetMockFilmResponse>())
    val filmState: StateFlow<List<GetMockFilmResponse>>
        get() = filmMutableState

    init {
        viewModelScope.launch {
            val dataMovies = api.getAllFilm()
            filmMutableState.value = dataMovies
        }
    }


    // THE MOVIE DB ================================================================================
    private val movies = MutableLiveData<GetMovies>()
    private val dataState: LiveData<GetMovies>
        get() = movies

    @SuppressLint("CoroutineCreationDuringComposition")
    fun latestMovie(page: Int, action: (LiveData<GetMovies>) -> Unit) {
        viewModelScope.launch {
            val data = apiRepository.getLatestMovies(page)
//            Log.d("DATA DI VIEWMODEL", a.getLatestMovies(page).toString())

            delay(2000)
            movies.value = data

            action(dataState)
        }
    }
}