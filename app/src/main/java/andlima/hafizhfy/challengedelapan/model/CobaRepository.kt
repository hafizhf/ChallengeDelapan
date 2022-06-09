@file:Suppress("unused", "unused")

package andlima.hafizhfy.challengedelapan.model

class CobaRepository {
    fun getCoba() : List<Coba> {
        return listOf(
            Coba("Coconut", "2004"),
            Coba("Mango", "2002"),
            Coba("Avocado", "2003"),
            Coba("Banana", "2001"),
            Coba("Dragon", "2077"),
        )
    }
}