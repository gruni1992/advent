fun main() {
    fun part1(input: List<String>): Int = countIncreases(input.map(String::toInt))
    fun part2(input: List<String>): Int = countIncreases(buildWindows(input.map(String::toInt)))


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    println(part1(testInput))
    println(part2(testInput))

    println("-----")

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

fun countIncreases(input: List<Int>) = input.filterIndexed { i, num ->
    if (i == 0) false
    else num > input[i - 1]
}.count()

fun buildWindows(input: List<Int>): List<Int> {
    return input.mapIndexedNotNull { i, num ->
        if (i == 0 || i == 1) null
        else num + input[i - 1] + input[i - 2]
    }
}