package day5

import util.Solution

sealed interface Inventory
data class Fresh(val lower: Long, val upper: Long) : Inventory
data class Ingredient(val id: Long) : Inventory

class Cafeteria(fileName: String?) : Solution<Inventory, Int>(fileName) {
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

    override fun solve1(data: List<Inventory>): Int {
        val fresh = data.filterIsInstance<Fresh>()
        val ingredients = data.filterIsInstance<Ingredient>()

        return ingredients.count {ingredient ->
            fresh.any { it.lower <= ingredient.id && ingredient.id <= it.upper }
        }
    }

    override fun solve2(data: List<Inventory>): Int {
        TODO("Not yet implemented")
    }
}