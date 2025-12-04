package day4

import util.Grid
import util.Helpers.Companion.toGrid
import util.Point
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
        val accessible = findAccessible(map, emptySet())
        return accessible.size
    }

    override fun solve2(data: List<List<Location>>): Int {
        val map = data.toGrid()

        tailrec fun removeRolls(removed: Set<Point>): Set<Point> {
            val accessible = findAccessible(map, removed)
            return if (accessible.isEmpty()) removed else removeRolls(removed + accessible)
        }

        return removeRolls(emptySet()).size
    }


    private fun findAccessible(map: Grid<Location>, removed: Set<Point>): Set<Point> {
        return map.entries
            .filter { (pos, content) -> pos !in removed && content == Location.PAPER }
            .filter { (pos, _) ->
                val accessiblePositions = pos
                    .getNeighbours(cardinal = false)
                    .count { it in removed || (map[it] ?: Location.EMPTY) == Location.EMPTY }
                accessiblePositions > 4
            }
            .map { it.key }
            .toSet()

    }
}