package day9

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import javax.swing.Spring.height

class MovieTheaterTest {
    private val sample1 = MovieTheater("/day9/sample1")

    @Test
    fun sample1star1() {
        assertEquals(50, sample1.star1())
    }
}