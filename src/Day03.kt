fun main() {
    val input = readInput("Day03")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

private fun part1(input: List<String>): Int {
    val gamma = calculatePart1(input, maxFun())
    val epsilon: String = calculatePart1(input, minFun())
    return gamma.toInt(2) * epsilon.toInt(2)
}

private fun calculatePart1(input: List<String>, compFun: (Map<Char, Int>) -> Char): String {
    return (0 until input.first().length)
        .map { i -> findBit(input, i, compFun) }
        .joinToString("")
}

private fun findBit(input: List<String>, positionIndex: Int, compFun: (Map<Char, Int>) -> Char): Char {
    return input.map { it[positionIndex] }
        .groupingBy { it }
        .eachCount()
        .let(compFun)
}

private fun minFun(): (Map<Char, Int>) -> Char = { if (it['0'] == it['1']) '0' else it.minByOrNull { entry ->  entry.value }!!.key }
private fun maxFun(): (Map<Char, Int>) -> Char = { if (it['0'] == it['1']) '1' else it.maxByOrNull { entry ->  entry.value }!!.key }

private fun part2(input: List<String>): Int {
    val oxygenRating = calculatePart2(input, maxFun())
    val co2Rating = calculatePart2(input, minFun())
    println(oxygenRating)
    println(co2Rating)
    return oxygenRating.toInt(2) * co2Rating.toInt(2)
}

private fun calculatePart2(input: List<String>, compFun: (Map<Char, Int>) -> Char, currentBit: Int = 0): String {
    if (input.size == 1) {
        return input.first()
    }
    return calculatePart2(input.filter { it[currentBit] == findBit(input, currentBit, compFun) }, compFun, currentBit + 1)
}




