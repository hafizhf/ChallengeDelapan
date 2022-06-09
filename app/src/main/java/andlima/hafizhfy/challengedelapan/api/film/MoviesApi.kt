package andlima.hafizhfy.challengedelapan.api.film

import andlima.hafizhfy.challengedelapan.model.film.GetMockFilmResponse
import andlima.hafizhfy.challengedelapan.model.film.GetMovies
import andlima.hafizhfy.challengedelapan.model.film.GetMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    // Mock API
    @GET("film")
    suspend fun getAllFilm() : List<GetMockFilmResponse>

    // The Movie DB API
    companion object {
        const val API_KEY = "b2ff233b44df252ae2d9d16cb264867a"
    }

    @GET("movie/latest")
    suspend fun getLatestMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): GetMovies

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): GetMoviesResponse

    @GET("/movie/{movie_id}/recommendations")
    suspend fun getRecommendationsByMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ) : GetMoviesResponse
}