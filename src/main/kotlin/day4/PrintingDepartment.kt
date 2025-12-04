package day4

import util.Helpers.Companion.toGrid
import util.Solution

enum class Location {
    PAPER,
    EMPTY
}

class PrintingDepartment(fileName: String?) : Solution<List<Location>, Int>(fileName) {
    override fun parse(line: String): List<Location> {
        return line.map { if (it == '@') Location.PAPER else Location.EMPTY }
    }

    override fun solve1(data: List<List<Location>>): Int {
        val map = data.toGrid()
        return map.entries.count {(pos, content) ->
            content == Location.PAPER && pos.getNeighbours(cardinal = false).count { map[it] == Location.PAPER } < 4
        }
    }

    override fun solve2(data: List<List<Location>>): Int {
        TODO("Not yet implemented")
    }
}