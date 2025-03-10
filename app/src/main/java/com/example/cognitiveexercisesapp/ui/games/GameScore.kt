package com.example.cognitiveexercisesapp.ui.games

class GameScore {

    // Function for calculating score based on difficulty level and time used. It scales on a
    // 1 to 100 scale with exponential growth. It also grants extra bonus points for higher difficulty.
    fun calculateScore(difficulty: Int, time: Int): Int {
        val baseScore = 100
        val timeBonus = 100 - time
        val difficultyBonus = difficulty * 2
        val score = baseScore + timeBonus + difficultyBonus
        return score
    }

}