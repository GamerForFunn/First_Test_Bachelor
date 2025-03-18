package com.example.cognitiveexercisesapp.ui.games.game2

import com.example.cognitiveexercisesapp.ui.data.GameInstructions
import com.example.cognitiveexercisesapp.ui.games.game1.calculateAmountAndRange

object Game2Config {
    val difficulty: Int
        get() = GameInstructions.difficulty.toInt() //Getting global difficulty so it can be imported to game
    const val rounds = 3 //Change to make the game have more rounds

    fun calculateDifficulty(): String { //Difficulty is a number between 1-100 so 1-33 easy, 33-66 medium, 66-100 hard
        val findDifficulty = GameInstructions.difficulty
        require(difficulty in 1..100) { "Difficulty must be between 1 and 100" }
        var difficultyGame2 = "easy" //Setting up the value
        difficultyGame2 = when{
            findDifficulty < 33 -> "easy"
            findDifficulty < 66 -> "medium"
            findDifficulty > 66 -> "hard"
            else -> "easy" //Always important to have a fallback value, even though it should never happen
        }
        return difficultyGame2
    }




}