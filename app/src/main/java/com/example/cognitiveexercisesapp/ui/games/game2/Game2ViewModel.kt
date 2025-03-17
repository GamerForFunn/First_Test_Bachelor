package com.example.testforbachelor.ui.game_2

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import android.graphics.Color as AndroidColor
import androidx.compose.ui.graphics.Color as ComposeColor
import com.example.cognitiveexercisesapp.R
import com.example.cognitiveexercisesapp.ui.data.game3model.Game3Difficulty
import com.example.cognitiveexercisesapp.ui.games.game2.Game2Config
import kotlinx.coroutines.flow.update

class Game2ViewModel : ViewModel() {
    private val _randomImage1 = MutableStateFlow(0)
    val randomImage1: StateFlow<Int> = _randomImage1.asStateFlow()

    private val _randomImage2 = MutableStateFlow(0)
    val randomImage2: StateFlow<Int> = _randomImage2.asStateFlow()

    private val _randomImage3 = MutableStateFlow(0)
    val randomImage3: StateFlow<Int> = _randomImage3.asStateFlow()

    private val _winnerImage = MutableStateFlow(0)
    val winnerImage: StateFlow<Int> = _winnerImage.asStateFlow()

    private val _game2WelcomeText = MutableStateFlow("Please select the correct image!")
    val game2WelcomeText: StateFlow<String> = _game2WelcomeText.asStateFlow()

    private val _timerText = MutableStateFlow("00:00")
    val timerText: StateFlow<String> = _timerText.asStateFlow()

    private val _isRetryVisible = MutableStateFlow(false)
    val isRetryVisible: StateFlow<Boolean> = _isRetryVisible.asStateFlow()

    private val _backgroundColor = MutableStateFlow(ComposeColor.White)
    val backgroundColor: StateFlow<ComposeColor> = _backgroundColor.asStateFlow()

    private var elapsedTime = 0L



    init {
        generateRandomImages()
    }

    private fun generateRandomImages() {
        val difficulty = Game2Config.calculateDifficulty()
        _randomImage1.update { getRandomImage(difficulty) }
        _randomImage2.update {
            var newImage = getRandomImage(difficulty)
            while (newImage == _randomImage1.value) {
                newImage = getRandomImage(difficulty)
            }
            newImage
        }
        _randomImage3.update {
            var newImage = getRandomImage(difficulty)
            while (newImage == _randomImage1.value || newImage == _randomImage2.value) {
                newImage = getRandomImage(difficulty)
            }
            newImage
        }
        _winnerImage.update {
            val random = (1..3).random()
            when (random) {
                1 -> _randomImage1.value
                2 -> _randomImage2.value
                3 -> _randomImage3.value
                else -> _randomImage1.value
            }
        }
    }

    private fun getRandomImage(difficulty : String): Int {
        var output = 404
        if (difficulty == "easy"){
        val images = listOf(
            R.drawable.appletest,
            R.drawable.bananatest,
            R.drawable.orangetest,
            R.drawable.lycheetest,
            R.drawable.broccolitest,
            R.drawable.cherrytest,
            R.drawable.peartest,
            R.drawable.tomatotest
        )
        output = images.random()
        }
        if(difficulty == "medium"){
            val images = listOf(
                R.drawable.celeb1,
                R.drawable.celeb2,
                R.drawable.celeb3,
                R.drawable.celeb4,
                R.drawable.celeb5,
                R.drawable.celeb6,
                R.drawable.celeb7,
                R.drawable.celeb8,
                R.drawable.celeb9

            )
            return images.random()
        }
        if(difficulty == "hard"){
            val images = listOf(
                R.drawable.boatimage1,
                R.drawable.boatimage2,
                R.drawable.boatimage3,
                R.drawable.boatimage4,
                R.drawable.boatimage5,
                R.drawable.boatimage6,
                R.drawable.boatimage7
            )
            output = images.random()
        }
        return output

    }
    fun onRetryClick(){resetGame()}


    fun startTimer() {
        //TODO
    }

    fun stopTimer() {
        //TODO
    }
    fun resetGame() {
        elapsedTime = 0
        _timerText.update { "00:00" }
        generateRandomImages()
        _game2WelcomeText.update { "Please select the correct image!" }
    }
}