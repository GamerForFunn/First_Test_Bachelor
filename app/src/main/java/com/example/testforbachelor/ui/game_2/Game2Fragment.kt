package com.example.testforbachelor.ui.game_2

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testforbachelor.databinding.FragmentGame2Binding

class Game2Fragment : Fragment() {

    private var _binding: FragmentGame2Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var isTimerRunning = false
    private var elapsedTime = 0L
    private lateinit var timerTextView: TextView
    private var timer: CountUpTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val game2ViewModel =
            ViewModelProvider(this)[Game2ViewModel::class.java]

        _binding = FragmentGame2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.game2Welcome
        game2ViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        //Function for generating random images
        //To find getRandomImage please look at game2ViewModel.kt
        val randomImage1 = game2ViewModel.getRandomImage()
            var randomImage2 = (game2ViewModel.getRandomImage())
            while(randomImage2 == randomImage1){ //Checking that the images are not the same
                randomImage2 = game2ViewModel.getRandomImage()
            }
            var randomImage3 = game2ViewModel.getRandomImage()
            while(randomImage3 == randomImage1 || randomImage3 == randomImage2){ //Again checking images are not the same
                randomImage3 = game2ViewModel.getRandomImage()

            }
            fun getWinnerImage(): Int { //Selects one of the images generated to be the correct one
                val random = (1..3).random()
                when (random) {
                    1 -> return randomImage3
                    2 -> return randomImage2
                    3 -> return randomImage1
                    else -> return randomImage1
                }
            }
            val winnerImage = getWinnerImage() //tried to have the "getWinnerImage" here but it didn't work
        //easier to make a separate function



        //Binds the buttons to a variable
        val leftImageButton: ImageButton = binding.imageButtonLeft
        val middleImageButton: ImageButton = binding.imageButtonMiddle
        val rightImageButton: ImageButton = binding.imageButtonRight
        val correctImageView: ImageView = binding.imageViewCorrect

        //Assigns the images to the buttons
        leftImageButton.setImageResource(randomImage1)
        middleImageButton.setImageResource(randomImage2)
        rightImageButton.setImageResource(randomImage3)
        correctImageView.setImageResource(winnerImage)



        //Checks for what button is correct
        leftImageButton.setOnClickListener { if (randomImage1 == winnerImage) { //Checks if the answer is correct
                correctAnswer()
            } else {
                wrongAnswer()
            }
        }
        rightImageButton.setOnClickListener { if (randomImage3 == winnerImage) { //Checks if the answer is correct
                correctAnswer()
            } else {
                wrongAnswer()}
        }
        middleImageButton.setOnClickListener { if (randomImage2 == winnerImage) { //Checks if the answer is correct
                correctAnswer()
            } else {
                wrongAnswer()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun correctAnswer(){ //Does an action when the answer is correct
        stopTimer()
        Toast.makeText(requireContext(), "Correct!", Toast.LENGTH_SHORT).show()

    }
    private fun wrongAnswer(){
        Toast.makeText(requireContext(), "Wrong!", Toast.LENGTH_SHORT).show()
    }//Does an action when the answer is wrong

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


}
