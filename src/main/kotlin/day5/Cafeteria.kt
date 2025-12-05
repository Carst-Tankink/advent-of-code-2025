package day5

import util.Solution
import kotlin.math.max
import kotlin.math.min

sealed interface Inventory
data class Fresh(val lower: Long, val upper: Long) : Inventory
data class Ingredient(val id: Long) : Inventory

class Cafeteria(fileName: String?) : Solution<Inventory, Long>(fileName) {
    override fun parse(line: String): Inventory? {
        return if (line.isBlank()) null else {
            val numbers = line.split("-").map { it.toLong() }
            if ((numbers.size == 2)) {
                Fresh(numbers[0], numbers[1])
            } else {
                Ingredient(numbers[0])
            }
        }
    }

    override fun solve1(data: List<Inventory>): Long {
        val fresh = data.filterIsInstance<Fresh>()
        val ingredients = data.filterIsInstance<Ingredient>()

        return ingredients.count { ingredient ->
            fresh.any { it.lower <= ingredient.id && ingredient.id <= it.upper }
        }.toLong()
    }

    override fun solve2(data: List<Inventory>): Long {
        val fresh = data.filterIsInstance<Fresh>().toSet()

        tailrec fun mergeRanges(left: Set<Fresh>, merged: Set<Fresh>): Set<Fresh> {
            return if (left.isEmpty()) merged else {
                val head = left.first()
                val tail = left.drop(1).toSet()
                val others = tail + merged
                val canMerge = others.find { other ->
                    head.lower <= other.upper && head.upper >= other.lower
                }

                if (canMerge == null) {
                    mergeRanges(tail, merged + head)
                } else {
                    val mergedRange = Fresh(
                        min(head.lower, canMerge.lower),
                        max(head.upper, canMerge.upper)

                    )
                    mergeRanges(tail - canMerge + mergedRange, merged )
                }
            }
        }

        val merged = mergeRanges(fresh, emptySet())
        return merged.sumOf { it.upper - it.lower + 1 }
    }
}