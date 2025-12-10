package day10

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FactoryTest {
    private val sample1 = Factory("/day10/sample1")

    @Test
    fun sample1star1() {
        assertEquals(7, sample1.star1())
    }
}