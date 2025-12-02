package day2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GiftShopTest {
    private val day2Folder = "/day2"

    private val sample1 = GiftShop("$day2Folder/sample1")


    @Test
    fun sample1star1() {
        assertEquals(1227775554L, sample1.star1())
    }

    @Test
    fun sample1star2() {
        assertEquals(4174379265L, sample1.star2())
    }
}