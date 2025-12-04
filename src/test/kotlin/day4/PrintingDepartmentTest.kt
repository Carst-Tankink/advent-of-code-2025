package day4

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PrintingDepartmentTest {
    val sample1 = PrintingDepartment("/day4/sample1")

    @Test
    fun sample1star1() {
        assertEquals(13, sample1.star1())
    }
}