package day3

import util.Helpers.Companion.pow
import util.Solution

class Lobby(fileName: String?) : Solution<List<Long>, Long>(fileName) {
    override fun parse(line: String): List<Long> {
        return line.map { it.digitToInt().toLong() }
    }

    override fun solve1(data: List<List<Long>>): Long {
        return data.sumOf { bank ->
            findMaxJoltage(0, bank, 2)
        }

    }

    override fun solve2(data: List<List<Long>>): Long {
        val maximums = data
            .map { bank -> findMaxJoltage(0L, bank, 12) }
        return maximums
            .sum()
    }

    private tailrec fun findMaxJoltage(joltage: Long, remainingBank: List<Long>, leftToFind: Int): Long {
        return if (leftToFind == 0) joltage else {
            val index = leftToFind - 1
            val largest = remainingBank.dropLast(index).max()
            findMaxJoltage(
                joltage + (largest * pow(10L, index.toLong())),
                remainingBank.dropWhile { it != largest }.drop(1),
                index
            )

        }
    }
}