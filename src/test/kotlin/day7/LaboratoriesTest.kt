package day7

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LaboratoriesTest {
    private val sample1 = Laboratories("/day7/sample1")

    @Test
    fun sample1star1() {
        assertEquals(21, sample1.star1())
    }
}