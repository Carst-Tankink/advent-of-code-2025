package day12

import util.Solution

sealed interface Situation

data class ShapeId(val id: Int) : Situation
data class ShapeLine(val content: List<Boolean>) : Situation

data class Required(
    val width: Int,
    val height: Int,
    val numbers: List<Int>
) : Situation

data class Shape(
    val id: Int,
    val lines: List<List<Boolean>>
)

class ChristmasTreeFarm(fileName: String?) : Solution<Situation, Int>(fileName) {
    override fun parse(line: String): Situation? {
        return when {
            line.contains("x") -> {
                val (area, gifts) = line.split(": ")
                val (width, height) = area.split("x").map { it.toInt() }

                Required(
                    width, height, gifts.split(" ").map { it.toInt() }
                )
            }

            line.contains(":") -> ShapeId(line.takeWhile { it != ':' }.toInt())
            else -> ShapeLine(line.map { it == '#' })
        }
    }

    override fun solve1(data: List<Situation>): Int {
        val shapes = buildShape(data.filter { it !is Required }, emptyList(), null)
        val shapeCount: List<Int> = shapes.map {shape -> 
            val lines = shape.lines
            lines.sumOf {
                l -> l.count { it }     
            }
            
        }
        return data.filterIsInstance<Required>().count {
            val size = it.width * it.height
            val fit = it.numbers.withIndex().sumOf { (idx, presents) ->
                presents * shapeCount[idx]

            }
            size >= fit
        }
    }

    private tailrec fun buildShape(
        lines: List<Situation>,
        acc: List<Shape>,
        currentShape: Shape?
    ): List<Shape> {
        return if (lines.isEmpty()) acc + listOfNotNull(currentShape) else {
            val first = lines.first()
            val tail = lines.drop(1)

            when (first) {
                is ShapeId -> buildShape(
                    tail,
                    acc + listOfNotNull(currentShape),
                    Shape(first.id, emptyList())
                )

                is ShapeLine -> {
                    val content: List<List<Boolean>> = currentShape!!.lines + listOf(first.content)
                    buildShape(
                        tail,
                        acc,
                        currentShape.copy(lines = content)
                    )
                }

                else -> {
                    error("Unexpected line")
                }
            }
        }
    }

    override fun solve2(data: List<Situation>): Int {
        TODO("Not yet implemented")
    }
}