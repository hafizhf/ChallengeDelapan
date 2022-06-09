package andlima.hafizhfy.challengedelapan.repository

import andlima.hafizhfy.challengedelapan.model.film.GetMockFilmResponse

class DummyRepository {
    fun getDummyMovie() = listOf(
        GetMockFilmResponse("Today", "Me", "1", "", "2077", "It's a film!", "Spyder-Man: No Way Home"),
        GetMockFilmResponse("Yesterday", "You", "2", "", "2037", "It's a film!", "Film Two"),
        GetMockFilmResponse("Day after tomorrow", "They", "3", "", "1887", "It's a film!", "Film Three")
    )
}