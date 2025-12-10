package day10

import util.Solution

data class Machine(
    val endState: List<Boolean>,
    val buttons: List<Set<Int>>,
    val joltages: List<Int>
)

class Factory(fileName: String?) : Solution<Machine, Long>(fileName) {
    override fun parse(line: String): Machine {
        val parts = line.split(" ")
        val endState = parts[0].drop(1).dropLast(1).map { it != '.' }
        val buttons = parts
            .drop(1)
            .dropLast(1)
            .map { line ->
                line
                    .drop(1)
                    .dropLast(1)
                    .split(",")
                    .map { it.toInt() }
                    .toSet()
            }

        val joltages = parts
            .last()
            .drop(1)
            .dropLast(1)
            .split(",")
            .map { it.toInt() }

        return Machine(
            endState,
            buttons,
            joltages
        )
    }

    override fun solve1(data: List<Machine>): Long {
        return data.sumOf { minimalPresses(it) }
    }

    private fun minimalPresses(m: Machine): Long {
        val startState = List(m.endState.size) { false }
        val distances = mutableMapOf<List<Boolean>, Long>(startState to 0).withDefault { Long.MAX_VALUE }

        tailrec fun dijkstra(
            todo: Set<List<Boolean>>,
            visited: Set<List<Boolean>>
        ): Long {
            return if (todo.isEmpty()) distances.getValue(m.endState) else {
                val next = todo.minBy { distances.getValue(it) }
                val distance = distances.getValue(next)
                val neighbours = getNeighbours(next, m.buttons)
                    .filter { it !in visited }
                
                neighbours.forEach {
                    val currDistances = distances.getValue(it)
                    val newDistance = distance + 1
                    
                    if (newDistance < currDistances) distances[it] = newDistance
                }
                dijkstra(todo - setOf(next) + neighbours, visited + setOf(next))
            }
        }

        return dijkstra(setOf(startState), emptySet())
    }

    private fun getNeighbours(
        state: List<Boolean>,
        buttons: List<Set<Int>>
    ): Set<List<Boolean>> {
        return buttons.map { pressed ->
            state.mapIndexed { idx, v -> if (idx in pressed) !v else v }
        }.toSet()
    }

    override fun solve2(data: List<Machine>): Long {
        TODO("Not yet implemented")
    }

}