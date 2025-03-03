package com.example.main.data.repository


import com.example.main.data.model.Difficulty
import com.example.main.data.model.WordPairingWordSet

class WordPairingRepository {
    // Word sets for different difficulties (easy, medium, hard)
    private val easyWordSets = listOf(
        WordPairingWordSet(
            word1 = "Big",
            word2 = "Large",
            word3 = "Small",
            correctPair = Pair("Big", "Large"),
            difficulty = Difficulty.EASY
        ),
        WordPairingWordSet(
            word1 = "Happy",
            word2 = "Sad",
            word3 = "Joyful",
            correctPair = Pair("Happy", "Joyful"),
            difficulty = Difficulty.EASY
        ),
        WordPairingWordSet(
            word1 = "Ugly",
            word2 = "Beautiful",
            word3 = "Pretty",
            correctPair = Pair("Beautiful", "Pretty"),
            difficulty = Difficulty.EASY
        )
    )

    private val mediumWordSets = listOf(
        WordPairingWordSet(
            word1 = "Eloquent",
            word2 = "Articulate",
            word3 = "Silent",
            correctPair = Pair("Eloquent", "Articulate"),
            difficulty = Difficulty.MEDIUM
        ),
        WordPairingWordSet(
            word1 = "Peculiar",
            word2 = "Unusual",
            word3 = "Common",
            correctPair = Pair("Peculiar", "Unusual"),
            difficulty = Difficulty.MEDIUM
        ),
        WordPairingWordSet(
            word1 = "Abundant",
            word2 = "Plentiful",
            word3 = "Scarce",
            correctPair = Pair("Abundant", "Plentiful"),
            difficulty = Difficulty.MEDIUM
        )
    )

    private val hardWordSets = listOf(
        WordPairingWordSet(
            word1 = "Ephemeral",
            word2 = "Transient",
            word3 = "Eternal",
            correctPair = Pair("Ephemeral", "Transient"),
            difficulty = Difficulty.HARD
        ),
        WordPairingWordSet(
            word1 = "Ubiquitous",
            word2 = "Omnipresent",
            word3 = "Rare",
            correctPair = Pair("Ubiquitous", "Omnipresent"),
            difficulty = Difficulty.HARD
        ),
        WordPairingWordSet(
            word1 = "Lethargic",
            word2 = "Torpid",
            word3 = "Energetic",
            correctPair = Pair("Lethargic", "Torpid"),
            difficulty = Difficulty.HARD
        )
    )

    fun getWordSetsByDifficulty(difficulty: Difficulty): List<WordPairingWordSet> {
        return when (difficulty) {
            Difficulty.EASY -> easyWordSets
            Difficulty.MEDIUM -> mediumWordSets
            Difficulty.HARD -> hardWordSets
        }
    }

    fun getRandomWordSet(difficulty: Difficulty): WordPairingWordSet {
        return getWordSetsByDifficulty(difficulty).random()
    }
}