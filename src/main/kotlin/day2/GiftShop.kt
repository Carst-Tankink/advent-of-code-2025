package day2

import util.Helpers.Companion.toDigits
import util.Solution

class GiftShop(fileName: String?) : Solution<List<Pair<Long, Long>>, Long>(fileName) {
    override fun parse(line: String): List<Pair<Long, Long>> {
        return line.split(",").map { rng ->
            val range = rng.split("-").map { it.toLong() }
            Pair(range[0], range[1])
        }
    }

    override fun solve1(data: List<List<Pair<Long, Long>>>): Long {
        return data.first().sumOf { (lower, upper) ->
            (lower..upper).filter { number ->
                val digits = number.toDigits()
                digits.size % 2 == 0 && isInvalid(digits, digits.size / 2)
            }.sum()
        }
    }

    override fun solve2(data: List<List<Pair<Long, Long>>>): Long {
        return data.first().sumOf { (lower, upper) ->
            (lower..upper).filter { number ->
                val digits = number.toDigits()
                digits.size > 1 && (1..digits.size / 2).any { isInvalid(digits, it) }
            }.sum()
        }
    }

    private fun isInvalid(digits: List<Int>, groupSize: Int): Boolean {
        val chunks = digits.chunked(groupSize)
        return chunks.zipWithNext().all { (x, y) -> x == y }
    }
}