package day11

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ReactorTest {
    private val sample1 = Reactor("/day11/sample1")

    @Test
    fun sample1star1() {
        assertEquals(5, sample1.star1())
    }
}