package day3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LobbyTest {
    private val day1Folder = "/day3"

    private val sample1 = Lobby("$day1Folder/sample1")

    @Test
    fun sample1star1() {
        assertEquals(357, sample1.star1())
    }

    @Test
    fun sample1star2() {
        assertEquals(3121910778619L, sample1.star2())
    }
}