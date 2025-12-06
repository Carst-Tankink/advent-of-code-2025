package day6

import util.Helpers.Companion.transpose
import util.Solution

sealed interface Homework
data class Number(val value: Long) : Homework
enum class Operator : Homework {
    PLUS,
    MULTIPLY
}

class TrashCompactor(fileName: String?) : Solution<List<Homework>, Long>(fileName) {
    override fun parse(line: String): List<Homework> {
        return line.split(" ").filter(String::isNotBlank)
            .map {
                when (it) {
                    "+" -> Operator.PLUS
                    "*" -> Operator.MULTIPLY
                    else -> Number(it.toLong())
                }
            }
    }

    override fun solve1(data: List<List<Homework>>): Long {
        return data
            .transpose()
            .sumOf { list ->
                val operator = list.filterIsInstance<Operator>().first()
                val numbers = list.filterIsInstance<Number>().map { it.value }
                when (operator) {
                    Operator.PLUS -> numbers.sum()
                    Operator.MULTIPLY -> numbers.fold(1L) { acc, number -> acc * number }
                }
            }
    }

    override fun solve2(data: List<List<Homework>>): Long {
        TODO("Not yet implemented")
    }
}