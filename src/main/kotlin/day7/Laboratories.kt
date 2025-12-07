package day7

import util.Solution

enum class POI {
    EMPTY,
    START,
    SPLITTER
}

class Laboratories(fileName: String?) : Solution<List<POI>, Long>(fileName) {
    override fun parse(line: String): List<POI> {
        return line.mapNotNull {
            when (it) {
                'S' -> POI.START
                '^' -> POI.SPLITTER
                else -> POI.EMPTY
            }
        }
    }

    override fun solve1(data: List<List<POI>>): Long {
        val positions = data
            .map { line ->
                line.withIndex()
                    .filter { it.value != POI.EMPTY }
                    .map { it.index }
                    .toSet()
            }
        val startPosition = positions.first()
        val splits = positions
            .drop(1)
            .filter { it.isNotEmpty() }
            .fold(Pair(0L, startPosition)) { (s, positions), splitPosition ->
                val (newSplits, newPositions) = positions
                    .fold(Pair(s, emptySet<Int>())) { (splits, newPositions), position ->
                    if (position in splitPosition) { 
                        Pair(splits + 1, newPositions + setOf(position - 1, position + 1))
                    } else {
                        Pair(splits, newPositions + position)
                    }
                    
                }
                Pair(newSplits, newPositions)
            }

        return splits.first
    }

    override fun solve2(data: List<List<POI>>): Long {
        TODO("Not yet implemented")
    }

}