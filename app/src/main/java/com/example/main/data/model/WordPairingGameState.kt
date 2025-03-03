package com.example.main.data.model

data class WordPairingGameState(
    val currentLevel: Int,
    val score: Int,
    val timeSpent: Long,  // Total time spent (for future scoring)
    val currentWordSet: WordPairingWordSet,
    val selectedIndices: List<Int> = emptyList(),
    val isCorrectPair: Boolean? = null,
    val timeLeft: Int = 30,  // New: Countdown from 30
    val statusMessage: String? = null
)
