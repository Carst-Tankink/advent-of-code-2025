package day11

import util.Solution

class Reactor(fileName: String?) : Solution<Pair<String, Set<String>>, Long>(fileName) {
    override fun parse(line: String): Pair<String, Set<String>>? {
        val (source, destinations) = line.split(": ")
        
        return source to destinations.split(" ").toSet()
    }

    override fun solve1(data: List<Pair<String, Set<String>>>): Long {
        val lookup = data.toMap()
        fun findPaths(todo: Set<List<String>>, pathsToOut: Set<List<String>>): Set<List<String>> {
            return if (todo.isEmpty()) pathsToOut else {
                val head = todo.first()
                val tail = todo.drop(1).toSet()
                val location = head.last()
                lookup[location]!!.flatMap { next -> 
                    val path = head + next
                    
                    if (next == "out") { 
                        findPaths(tail, pathsToOut + setOf(path))
                    } else {
                        findPaths(tail + setOf(path), pathsToOut)
                    }
                }.toSet()
            }
        }
        
        return findPaths(setOf(listOf("you")), emptySet()).size.toLong()
    }

    override fun solve2(data: List<Pair<String, Set<String>>>): Long {
        TODO("Not yet implemented")
    }
}