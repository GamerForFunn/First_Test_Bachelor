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

    // Easy words norwegian
    private val easyWordSetsNO = listOf(
        WordPairingWordSet(
            word1 = "Stor",
            word2 = "Liten",
            word3 = "Svær",
            correctPair = Pair("Stor", "Svær"),
            difficulty = Game3Difficulty.EASY
        ),
        WordPairingWordSet(
            word1 = "Trist",
            word2 = "Glad",
            word3 = "Lykkelig",
            correctPair = Pair("Glad", "Lykkelig"),
            difficulty = Game3Difficulty.EASY
        ),
        WordPairingWordSet(
            word1 = "Stygg",
            word2 = "Vakker",
            word3 = "Pen",
            correctPair = Pair("Vakker", "Pen"),
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

    // Medium words norwegian
    private val mediumWordSetsNO = listOf(
        WordPairingWordSet(
            word1 = "Veltalende",
            word2 = "Artikulert",
            word3 = "Taus",
            correctPair = Pair("Veltalende", "Artikulert"),
            difficulty = Game3Difficulty.MEDIUM
        ),
        WordPairingWordSet(
            word1 = "Merkelig",
            word2 = "Normal",
            word3 = "Usedvanlig",
            correctPair = Pair("Merkelig", "Usedvanlig"),
            difficulty = Game3Difficulty.MEDIUM
        ),
        WordPairingWordSet(
            word1 = "Rikelig",
            word2 = "Massevis",
            word3 = "Begrenset",
            correctPair = Pair("Rikelig", "Massevis"),
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

    private val hardWordSetsNO = listOf(
        WordPairingWordSet(
            word1 = "Grusom",
            word2 = "Utmerket",
            word3 = "Fortreffelig",
            correctPair = Pair("Fortreffelig", "Utmerket"),
            difficulty = Game3Difficulty.HARD
        ),
        WordPairingWordSet(
            word1 = "Besynderlig",
            word2 = "Underlig",
            word3 = "Alminnelig",
            correctPair = Pair("Besynderlig", "Underlig"),
            difficulty = Game3Difficulty.HARD
        ),
        WordPairingWordSet(
            word1 = "Utsøkt",
            word2 = "Tarvelig",
            word3 = "Ypperlig",
            correctPair = Pair("Utsøkt", "Ypperlig"),
            difficulty = Game3Difficulty.HARD
        )
    )

    fun getWordSetsByDifficulty(difficulty: Game3Difficulty): List<WordPairingWordSet>{
        return when (difficulty){
            Game3Difficulty.EASY -> easyWordSetsNO
            Game3Difficulty.MEDIUM -> mediumWordSetsNO
            Game3Difficulty.HARD -> hardWordSetsNO
        }
    }

    fun getRandomWordSet(difficulty: Game3Difficulty): WordPairingWordSet{
        return getWordSetsByDifficulty(difficulty).random()
    }
}