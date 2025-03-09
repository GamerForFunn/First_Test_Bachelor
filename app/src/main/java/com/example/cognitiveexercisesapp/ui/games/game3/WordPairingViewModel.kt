package com.example.cognitiveexercisesapp.ui.games.game3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cognitiveexercisesapp.ui.data.game3model.Game3Difficulty
import com.example.cognitiveexercisesapp.ui.data.game3model.WordPairingGameState
import com.example.cognitiveexercisesapp.ui.data.game3model.WordPairingWordSet
import com.example.cognitiveexercisesapp.ui.data.WordPairingRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WordPairingViewModel : ViewModel() {

    // Set a maximum amount of levels to 5
    private companion object {
        const val MAX_LEVEL = 5
    }

    // LiveData to hold the game state
    private val _gameState = MutableLiveData<WordPairingGameState>()
    val gameState: LiveData<WordPairingGameState> get() = _gameState

    // Repository containing word sets
    private val repository = WordPairingRepository()

    // Set starting difficulty
    private var currentDifficulty = Game3Difficulty.EASY

    // Track selected words AND their indices
    private var selectedWords = mutableListOf<String>()
    private var selectedIndices = mutableListOf<Int>()

    //Timer
    private var timerJob: Job? = null

    // Initialize the game with the first level
    init {
        startNewGame()
    }

    // Start a new game
    private fun startNewGame() {
        _gameState.value = WordPairingGameState(
            currentLevel = 1,
            score = 0,
            timeSpent = 0,
            currentWordSet = getRandomWordSet(1),
            selectedIndices = emptyList(),
            isCorrectPair = null,
            difficulty = currentDifficulty,
            timeLeft = 30 // Reset timer
        )
        startTimer() // Start the countdown
    }

    // Start or restart the timer
    private fun startTimer() {
        timerJob?.cancel() // Cancel any existing timer
        timerJob = viewModelScope.launch {
            while ((_gameState.value?.timeLeft ?: 0) > 0) {
                delay(1000L)
                _gameState.value = _gameState.value?.copy(
                    timeLeft = _gameState.value!!.timeLeft - 1
                )
                // Handle timeout
                if (_gameState.value?.timeLeft == 0) {
                    handleTimeout()
                    break
                }
            }
        }
    }

    // Handle timeout (+ automatically set answer to wrong)
    private fun handleTimeout() {
        _gameState.value = _gameState.value?.copy(
            statusMessage = "Oops! You ran out of time ⌛️",
            selectedIndices = listOf(0, 1, 2),
            isCorrectPair = false // Force incorrect state
        )
        viewModelScope.launch {
            delay(2000) // Show red color for 2 seconds
            resetSelection()
            startTimer() // Restart timer for next attempt
        }
    }

    // Function to handle word selection
    fun onWordSelected(word: String, index: Int) { // Add index parameter
        if (selectedWords.size < 2) {
            selectedWords.add(word)
            selectedIndices.add(index) // Track the index

            // Immediately update the UI with selected indices
            _gameState.value = _gameState.value?.copy(
                selectedIndices = selectedIndices.toList(),
                isCorrectPair = null // Reset correctness until two are selected
            )
        }

        if (selectedWords.size == 2) {
            timerJob?.cancel() // Pause timer during evaluation
            val currentWordSet = _gameState.value?.currentWordSet
            val correctPair = currentWordSet?.correctPair

            // Compare selected words as sets (order-agnostic)
            val isCorrect = if (correctPair != null) {
                selectedWords.toSet() == setOf(correctPair.first, correctPair.second)
            } else {
                false
            }

            // Update game state with correctness AND indices
            _gameState.value = _gameState.value?.copy(
                selectedIndices = selectedIndices.toList(), // Expose indices
                isCorrectPair = isCorrect // Track correctness
            )

            if (isCorrect) {
                showSuccess()
                updateScoreAndLevel()
            } else {
                showError()
            }
        }
    }

    fun resetSelection() {
        selectedWords.clear()
        selectedIndices.clear()
        _gameState.value = _gameState.value?.copy(
            statusMessage = null,
            selectedIndices = emptyList(),
            isCorrectPair = null
        )
        startTimer() // Restart timer for new attempt
    }

    // Function to update score and level
    private fun updateScoreAndLevel() {
        val currentState = _gameState.value ?: return
        val newLevel = currentState.currentLevel + 1
        if (newLevel > MAX_LEVEL) {
            startNewGame() // Reset to level 1
            // Here we should calculate the total score and redirect to game summary screen instead
        } else {
            // Proceed to next level
            _gameState.value = currentState.copy(
                score = currentState.score + 1,
                currentLevel = newLevel,
                currentWordSet = getRandomWordSet(newLevel),
                difficulty = currentDifficulty,
                timeLeft = 30 // Reset timer for the new level
            )
            startTimer() // Restart timer
        }
    }

    // Function to show an error (e.g., "Oops!")
    private fun showError() {
        _gameState.value = _gameState.value?.copy(
            statusMessage = "🚨 Oops! 🚨" // Set error message
        )
    }

    private fun showSuccess() {
        _gameState.value = _gameState.value?.copy(
            statusMessage = "✨ Well done! ✨" // Set success message
        )
    }

    // Function to get a random WordSet
    private fun getRandomWordSet(level: Int): WordPairingWordSet {
        // For level 1-2: EASY, level 3-4: MEDIUM, level 5: HARD
        val difficulty = when (level) {
            1, 2 -> Game3Difficulty.EASY
            3, 4 -> Game3Difficulty.MEDIUM
            else -> Game3Difficulty.HARD
        }

        // Update the difficulty
        currentDifficulty = difficulty

        return repository.getRandomWordSet(difficulty)
    }

    // Cleanup
    override fun onCleared() {
        timerJob?.cancel()
        super.onCleared()
    }
}