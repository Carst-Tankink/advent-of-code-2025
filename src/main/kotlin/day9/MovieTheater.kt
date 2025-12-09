package day9

import util.Point
import util.Solution
import kotlin.math.absoluteValue

class MovieTheater(fileName: String?) : Solution<Point, Long>(fileName) {
    override fun parse(line: String): Point? {
        val (x, y) = line.split(",").map { it.toLong() }
        
        return Point(x, y)
    }

    override fun solve1(data: List<Point>): Long {
        val pairs = makeUniquePairs(data, emptySet())
        return pairs.maxOf { 
            val width = (it.first.x - it.second.x).absoluteValue + 1
            val height = (it.first.y - it.second.y).absoluteValue + 1
            
            width * height
        }
    }

    private tailrec fun makeUniquePairs(data: List<Point>, acc: Set<Pair<Point, Point>>): Set<Pair<Point, Point>> {
        return if (data.isEmpty()) acc else {
            val head = data.first()
            val tail = data.drop(1)

            makeUniquePairs(tail, acc + tail.map { head to it }.toSet())
        }
    }

    override fun solve2(data: List<Point>): Long {
        TODO("Not yet implemented")
    }
}