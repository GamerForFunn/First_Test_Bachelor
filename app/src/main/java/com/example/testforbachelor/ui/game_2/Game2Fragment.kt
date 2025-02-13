package com.example.testforbachelor.ui.game_2

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testforbachelor.databinding.FragmentGame2Binding

class Game2Fragment : Fragment() {

    // CountUpTimer inner class moved to the top
    inner class CountUpTimer(millisInFuture: Long, countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {

        override fun onTick(millisUntilFinished: Long) {
            elapsedTime += 1000
            val seconds = elapsedTime / 1000
            timerTextView.text = seconds.toString()
        }

        override fun onFinish() {
            // This will never be called because we use Long.MAX_VALUE
        }
    }

    private var _binding: FragmentGame2Binding? = null
    private val binding get() = _binding!!

    private lateinit var timerTextView: TextView
    private var timer: CountUpTimer? = null
    private var isTimerRunning = false
    private var elapsedTime = 0L
    private lateinit var game2RootLayout: ConstraintLayout
    private lateinit var retryButton: Button
    private var randomImage1: Int = 0 // Initialize with a default value
    private var randomImage2: Int = 0 // Initialize with a default value
    private var randomImage3: Int = 0 // Initialize with a default value
    private var winnerImage: Int = 0 // Initialize with a default value
    private lateinit var leftImageButton: ImageButton
    private lateinit var middleImageButton: ImageButton
    private lateinit var rightImageButton: ImageButton
    private lateinit var correctImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val game2ViewModel =
            ViewModelProvider(this)[Game2ViewModel::class.java]

        _binding = FragmentGame2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        timerTextView = binding.timerTextView
        game2RootLayout = binding.game2RootLayout
        retryButton = binding.retryButton

        val textView: TextView = binding.game2Welcome
        game2ViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        leftImageButton = binding.imageButtonLeft
        middleImageButton = binding.imageButtonMiddle
        rightImageButton = binding.imageButtonRight
        correctImageView = binding.imageViewCorrect

        // Initialize random images and winner image here
        generateRandomImages(game2ViewModel)

        // Set click listeners for the ImageButtons
        leftImageButton.setOnClickListener {
            // Code to execute when leftImageButton is clicked
            if (randomImage1 == winnerImage) {
                correctAnswer()
            } else {
                wrongAnswer()
            }
        }

        middleImageButton.setOnClickListener {
            // Code to execute when middleImageButton is clicked
            if (randomImage2 == winnerImage) {
                correctAnswer()
            } else {
                wrongAnswer()
            }
        }

        rightImageButton.setOnClickListener {
            // Code to execute when rightImageButton is clicked
            if (randomImage3 == winnerImage) {
                correctAnswer()
            } else {
                wrongAnswer()
            }
        }
        retryButton.setOnClickListener {
            resetGame(game2ViewModel)
        }
        startTimer()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        stopTimer()
    }

    private fun correctAnswer() {
        stopTimer()
        game2RootLayout.setBackgroundColor(Color.GREEN) // Changed to GREEN
        retryButton.visibility = View.VISIBLE
        val seconds = elapsedTime / 1000
        Toast.makeText(requireContext(), "Correct! Time: $seconds seconds", Toast.LENGTH_SHORT).show()
    }

    private fun wrongAnswer() {
        game2RootLayout.setBackgroundColor(Color.RED)
        retryButton.visibility = View.VISIBLE
        stopTimer()
        Toast.makeText(requireContext(), "Wrong!", Toast.LENGTH_SHORT).show()
    }

    private fun startTimer() {
        if (!isTimerRunning) {
            timer = CountUpTimer(Long.MAX_VALUE, 1000)
            timer?.start()
            isTimerRunning = true
        }
    }

    private fun stopTimer() {
        if (isTimerRunning) {
            timer?.cancel()
            isTimerRunning = false
        }
    }
    private fun generateRandomImages(game2ViewModel: Game2ViewModel) {
        randomImage1 = game2ViewModel.getRandomImage()
        randomImage2 = game2ViewModel.getRandomImage()
        while (randomImage2 == randomImage1) {
            randomImage2 = game2ViewModel.getRandomImage()
        }
        randomImage3 = game2ViewModel.getRandomImage()
        while (randomImage3 == randomImage1 || randomImage3 == randomImage2) {
            randomImage3 = game2ViewModel.getRandomImage()
        }
        winnerImage = // This should never happen, but it's good to have a default
            run {
                val random = (1..3).random()
                // This should never happen, but it's good to have a default
                when (random) {
                    1 -> randomImage1
                    2 -> randomImage2
                    3 -> randomImage3
                    else -> randomImage1 // This should never happen, but it's good to have a default
                }
            } // <-- The () here executes the lambda immediately
        leftImageButton.setImageResource(randomImage1)
        middleImageButton.setImageResource(randomImage2)
        rightImageButton.setImageResource(randomImage3)
        correctImageView.setImageResource(winnerImage)
    }
    private fun resetGame(game2ViewModel: Game2ViewModel) {
        game2RootLayout.setBackgroundColor(Color.WHITE)
        retryButton.visibility = View.GONE
        elapsedTime = 0
        timerTextView.text = "0"
        generateRandomImages(game2ViewModel)
        startTimer()
    }
}