package com.example.main.data.model

data class WordPairingWordSet(
    val word1: String,
    val word2: String,
    val word3: String,
    val correctPair: Pair<String, String>,
    val difficulty: Difficulty
)