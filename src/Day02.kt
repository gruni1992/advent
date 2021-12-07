fun main() {
    fun part1(input: List<String>): Int {
        val orders = input.map { it.split(" ") }
            .groupBy({ it[0] }, { it[1].toInt() })
        val x = orders["forward"]!!.sum()
        val y = orders["down"]!!.sum() - orders["up"]!!.sum()
        return x * y
    }

    data class Command(val name: String, val amount: Int)
    data class Step(val aim: Int = 0, val x: Int = 0, val y: Int = 0)


    fun part2(input: List<String>): Int {
        return input.map { it.split(" ") }
            .map { Command(it[0], it[1].toInt()) }
            .fold(Step()) { acc, command ->
                when (command.name) {
                    "down" -> acc.copy(aim = acc.aim + command.amount)
                    "up" -> acc.copy(aim = acc.aim - command.amount)
                    "forward" -> { acc.copy(x = acc.x + command.amount, y = acc.y + acc.aim * command.amount) }
                    else -> throw IllegalArgumentException()
                }
            }.let { it.x * it.y }
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

