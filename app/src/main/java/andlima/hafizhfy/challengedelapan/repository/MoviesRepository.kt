package andlima.hafizhfy.challengedelapan.repository

import andlima.hafizhfy.challengedelapan.api.film.MoviesApi
import andlima.hafizhfy.challengedelapan.model.film.GetMovies
import andlima.hafizhfy.challengedelapan.model.film.GetMoviesResponse
import android.util.Log
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: MoviesApi) {
    suspend fun getAllFilm() = api.getAllFilm()

    // The Movie DB
    suspend fun getLatestMovies(page: Int) = api.getLatestMovies(page = page)
    suspend fun getPopularMovies(page: Int) = api.getPopularMovies(page = page)
    suspend fun getRecommendationsByMovie(movie_id: Int, page: Int) = api.getRecommendationsByMovie(movie_id = movie_id, page = page)
}