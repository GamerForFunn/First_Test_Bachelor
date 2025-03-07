package com.example.testforbachelor.ui.game_2

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import android.graphics.Color as AndroidColor
import androidx.compose.ui.graphics.Color as ComposeColor
import com.example.cognitiveexercisesapp.R
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
        _randomImage1.update { getRandomImage() }
        _randomImage2.update {
            var newImage = getRandomImage()
            while (newImage == _randomImage1.value) {
                newImage = getRandomImage()
            }
            newImage
        }
        _randomImage3.update {
            var newImage = getRandomImage()
            while (newImage == _randomImage1.value || newImage == _randomImage2.value) {
                newImage = getRandomImage()
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

    private fun getRandomImage(): Int {
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
        return images.random()
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