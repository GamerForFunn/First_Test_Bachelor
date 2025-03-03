package com.example.cognitiveexercisesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cognitiveexercisesapp.ui.theme.CognitiveExercisesAppTheme
import com.example.cognitiveexercisesapp.ui.theme.CorrectAnswer
import com.example.cognitiveexercisesapp.ui.theme.WrongAnswer
import com.example.gameone2.Game1


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CognitiveExercisesAppTheme {
                Game1(10)

                /*Game1(40)*/
            }
        }
    }
}
