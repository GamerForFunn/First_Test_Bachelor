package com.example.cognitiveexercisesapp.ui.data.game3model

 data class WordPairingWordSet(
     val word1: String,
     val word2: String,
     val word3: String,
     val correctPair: Pair<String, String>,
     val difficulty: Game3Difficulty
 )