package day6

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TrashCompactorTest {

    private val sample1 = TrashCompactor("/day6/sample1")

    @Test
    fun sample1star1() {
        assertEquals(4277556, sample1.star1())
    }

    @Test
    fun sample1star2() {
        assertEquals(3263827, sample1.star2())
    }
}