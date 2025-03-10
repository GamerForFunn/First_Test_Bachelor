package com.example.cognitiveexercisesapp.ui.games.game2

import com.example.cognitiveexercisesapp.ui.data.GameInstructions
import com.example.cognitiveexercisesapp.ui.games.game1.calculateAmountAndRange

object Game2Config {
    val difficulty: Int
        get() = GameInstructions.difficulty.toInt()
    val rounds = 3
    val showNextClick = false



    val amount: Int
        get() = calculateAmountAndRange(difficulty).first
    val range: Int
        get() = calculateAmountAndRange(difficulty).second

    fun calculateAmountAndRange(difficulty: Int): Pair<Int, Int> {
        require(difficulty in 1..100) { "Difficulty must be between 1 and 100" }

        val amount = 3 + (difficulty * 15) / 100  // Scales from 3 to 20
        val range = 20 + (difficulty * 79) / 100  // Scales from 20 to 99

        return Pair(amount, range)
    }




}