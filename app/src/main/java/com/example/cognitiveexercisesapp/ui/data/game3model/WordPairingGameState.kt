package com.example.cognitiveexercisesapp.ui.data.game3model

data class WordPairingGameState(
    val currentLevel: Int,
    val score: Int,
    val timeSpent: Long,
    val currentWordSet: WordPairingWordSet,
    val selectedIndices: List<Int> = emptyList(),
    val isCorrectPair: Boolean? = null,
    val timeLeft: Int = 30,
    val difficulty: Game3Difficulty = Game3Difficulty.EASY,
    val statusMessage: String? = null
)