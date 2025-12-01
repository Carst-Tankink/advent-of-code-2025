package day1

import util.Solution
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.sign

class SecretEntrance(fileName: String?) : Solution<Int, Int>(fileName) {
    override fun parse(line: String): Int {
        val direction = line.first()
        val number = line.drop(1).toInt()
        return if (direction == 'R') { number } else { -number }
    }


    override fun solve1(data: List<Int>): Int {
        return data.fold(Pair(50, 0)) { (pos, zeroes), instruction ->
            val newPos = (pos + instruction).mod(100)
            val newZeroes = if (newPos == 0) zeroes + 1 else zeroes
            Pair(newPos, newZeroes)
        }.second
    }

    override fun solve2(data: List<Int>): Int {
        return data.fold(Pair(50, 0)) { (pos, zeroes), instruction ->
            val fullRevolutions = instruction.div(100).absoluteValue
            val ins = instruction % 100

            val rawPos = (pos + ins)
            val newPos = rawPos.mod(100)
            val pastZeros = (pos != 0 && rawPos !in 0..100) || newPos == 0

            Pair(newPos, zeroes + fullRevolutions + if(pastZeros) 1 else 0 )
        }.second
    }
}