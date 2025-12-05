package day5

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CafeteriaTest {

    private val sample1 = Cafeteria("/day5/sample1")

    @Test
    fun sample1star1() {
        assertEquals(3, sample1.star1())
    }
}