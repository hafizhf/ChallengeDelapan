package andlima.hafizhfy.challengedelapan.model

class CobaRepository {
    fun getCoba() : List<Coba> {
        return listOf(
            Coba("Kepala", "2004"),
            Coba("Mangga", "2002"),
            Coba("Alpukat", "2003"),
            Coba("Jambu", "2001"),
            Coba("Naga", "2077"),
        )
    }
}