package day1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SecretEntranceTest {
    private val day1Folder = "/day1"

    private val sample1 = SecretEntrance("$day1Folder/sample1")

    @Test
    fun sampleStar1() {
        assertEquals(3, sample1.star1())
    }

    @Test
    fun sampleStar2() {
        assertEquals(6, sample1.star2())
    }

}