package com.example.cognitiveexercisesapp.ui.games.game1

import com.example.cognitiveexercisesapp.ui.data.GameInstructions


object Game1Config {
    val difficulty: Int
        get() = GameInstructions.difficulty.toInt()
    val rounds = 1
    val showNextClick = false

    val columns = 4
    val rows = 7

    val amount: Int
        get() = calculateAmountAndRange(difficulty).first
    val range: Int
        get() = calculateAmountAndRange(difficulty).second
}

fun calculateAmountAndRange(difficulty: Int): Pair<Int, Int> {
    require(difficulty in 1..100) { "Difficulty must be between 1 and 100" }

    val amount = 3 + (difficulty * 15) / 100  // Scales from 3 to 20
    val range = 20 + (difficulty * 79) / 100  // Scales from 20 to 99

    return Pair(amount, range)
}


fun getAmountAndRangeBasedOnDifficulty(): List<Int?> {

    require(Game1Config.amount in 1..(Game1Config.columns * Game1Config.rows)) { "Amount must be between 1 and columns * rows" }
    require(Game1Config.range >= Game1Config.amount) { "Range must be at least equal to amount" }

    // Generate `amount` random numbers within the range
    val numbers = (1..Game1Config.range).shuffled().take(Game1Config.amount)

    // Create a list with `columns * rows` spaces filled with null
    val gridSize = Game1Config.columns * Game1Config.rows
    val scatteredList = MutableList<Int?>(gridSize) { null }

    // Get random positions to place the numbers
    val positions = (0 until gridSize).shuffled().take(Game1Config.amount)

    // Place the numbers in the random positions
    positions.forEachIndexed { index, pos ->
        scatteredList[pos] = numbers[index]
    }

    return scatteredList
}
