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
            (lower..upper).filter { number -> isInvalid(number) }.sum()
        }
    }

    private fun isInvalid(number: Long): Boolean {
        val digits: List<Int> = number.toDigits()
        return if (digits.size % 2 == 0) {
            val first = digits.take(digits.size / 2)
            val second = digits.drop(digits.size / 2)
            first.size == second.size && first.zip(second).all { pair -> pair.first == pair.second }
        } else {
            false
        }
    }

    override fun solve2(data: List<List<Pair<Long, Long>>>): Long {
        TODO("Not yet implemented")
    }
}