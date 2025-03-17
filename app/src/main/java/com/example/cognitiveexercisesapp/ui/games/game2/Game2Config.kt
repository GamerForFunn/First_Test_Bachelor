package com.example.cognitiveexercisesapp.ui.games.game2

import com.example.cognitiveexercisesapp.ui.data.GameInstructions
import com.example.cognitiveexercisesapp.ui.games.game1.calculateAmountAndRange

object Game2Config {
    val difficulty: Int
        get() = GameInstructions.difficulty.toInt()
    val rounds = 3
    val showNextClick = false





    fun calculateDifficulty(): String {
        val findDifficulty = GameInstructions.difficulty
        require(difficulty in 1..100) { "Difficulty must be between 1 and 100" }
        var difficultyGame2 = "easy"
        difficultyGame2 = when{
            findDifficulty < 33 -> "easy"
            findDifficulty < 66 -> "medium"
            findDifficulty > 66 -> "hard"
            else -> "easy"
        }
        return difficultyGame2
    }




}