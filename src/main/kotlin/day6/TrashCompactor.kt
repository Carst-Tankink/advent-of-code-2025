package day6

import util.Helpers.Companion.transpose
import util.Solution

sealed interface Homework
data class Number(val value: Long) : Homework
enum class Operator : Homework {
    PLUS,
    MULTIPLY
}

class TrashCompactor(fileName: String?) : Solution<String, Long>(fileName) {
    override fun parse(line: String): String = line

    override fun solve1(data: List<String>): Long {
        val assignments: List<List<Homework>> = data.map { line ->
            line.split(" ").filter(String::isNotBlank)
                .map {
                    when (it) {
                        "+" -> Operator.PLUS
                        "*" -> Operator.MULTIPLY
                        else -> Number(it.toLong())
                    }
                }
        }
        return assignments
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

    override fun solve2(data: List<String>): Long {
        val transposed = data.map { line -> line.toList() }.transpose()
        tailrec fun findProblems(
            lists: List<List<Char>>,
            acc: List<Pair<Operator, List<Long>>>,
            current: Pair<Operator, List<Long>>?
        ): List<Pair<Operator, List<Long>>> {
            return if (lists.isEmpty()) acc + current!! else {
                val head = lists.first()
                val tail = lists.drop(1)
                if (head.joinToString("").isBlank()) {
                    findProblems(tail, acc + current!!, null)
                } else {
                    val number = head
                        .dropWhile { it == ' ' }
                        .takeWhile { it.isDigit() }
                        .joinToString("")
                        .toLong()
                    val last = head.last()
                    when (last) {
                        '*' -> findProblems(tail, acc, Pair(Operator.MULTIPLY, listOf(number)))
                        '+' -> findProblems(tail, acc, Pair(Operator.PLUS, listOf(number)))
                        else -> findProblems(tail, acc, Pair(current!!.first, current!!.second + listOf(number)))
                    }
                }
            }
        }

        val problems = findProblems(transposed, emptyList(), null)

        return problems.sumOf { (operator, numbers) ->
            when (operator) {
                Operator.PLUS -> numbers.sum()
                Operator.MULTIPLY -> numbers.fold(1L) { acc, number -> acc * number }
            }
        }
    }
}