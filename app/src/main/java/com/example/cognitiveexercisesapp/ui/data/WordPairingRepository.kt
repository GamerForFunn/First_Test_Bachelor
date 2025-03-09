package com.example.cognitiveexercisesapp.ui.data

import com.example.cognitiveexercisesapp.ui.data.game3model.Game3Difficulty
import com.example.cognitiveexercisesapp.ui.data.game3model.WordPairingWordSet

class WordPairingRepository {
    // Word sets for different difficulties (easy, medium, hard)
    //Easy words
    private val easyWordSets = listOf(
        WordPairingWordSet(
            word1 = "Big",
            word2 = "Large",
            word3 = "Small",
            correctPair = Pair("Big", "Large"),
            difficulty = Game3Difficulty.EASY
        ),
        WordPairingWordSet(
            word1 = "Happy",
            word2 = "Sad",
            word3 = "Joyful",
            correctPair = Pair("Happy", "Joyful"),
            difficulty = Game3Difficulty.EASY
        ),
        WordPairingWordSet(
            word1 = "Ugly",
            word2 = "Beautiful",
            word3 = "Pretty",
            correctPair = Pair("Beautiful", "Pretty"),
            difficulty = Game3Difficulty.EASY
        )
    )

    // Medium words
    private val mediumWordSets = listOf(
        WordPairingWordSet(
            word1 = "Eloquent",
            word2 = "Articulate",
            word3 = "Silent",
            correctPair = Pair("Eloquent", "Articulate"),
            difficulty = Game3Difficulty.MEDIUM
        ),
        WordPairingWordSet(
            word1 = "Peculiar",
            word2 = "Unusual",
            word3 = "Common",
            correctPair = Pair("Peculiar", "Unusual"),
            difficulty = Game3Difficulty.MEDIUM
        ),
        WordPairingWordSet(
            word1 = "Abundant",
            word2 = "Plentiful",
            word3 = "Scarce",
            correctPair = Pair("Abundant", "Plentiful"),
            difficulty = Game3Difficulty.MEDIUM
        )
    )
    // Hard words
    private val hardWordSets = listOf(
        WordPairingWordSet(
            word1 = "Ephemeral",
            word2 = "Transient",
            word3 = "Eternal",
            correctPair = Pair("Ephemeral", "Transient"),
            difficulty = Game3Difficulty.HARD
        ),
        WordPairingWordSet(
            word1 = "Ubiquitous",
            word2 = "Omnipresent",
            word3 = "Rare",
            correctPair = Pair("Ubiquitous", "Omnipresent"),
            difficulty = Game3Difficulty.HARD
        ),
        WordPairingWordSet(
            word1 = "Lethargic",
            word2 = "Torpid",
            word3 = "Energetic",
            correctPair = Pair("Lethargic", "Torpid"),
            difficulty = Game3Difficulty.HARD
        )
    )

    fun getWordSetsByDifficulty(difficulty: Game3Difficulty): List<WordPairingWordSet>{
        return when (difficulty){
            Game3Difficulty.EASY -> easyWordSets
            Game3Difficulty.MEDIUM -> mediumWordSets
            Game3Difficulty.HARD -> hardWordSets
        }
    }

    fun getRandomWordSet(difficulty: Game3Difficulty): WordPairingWordSet{
        return getWordSetsByDifficulty(difficulty).random()
    }
}