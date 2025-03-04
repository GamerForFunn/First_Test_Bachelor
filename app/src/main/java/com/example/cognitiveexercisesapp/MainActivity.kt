package com.example.cognitiveexercisesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cognitiveexercisesapp.ui.components.ExerciseFinished
import com.example.cognitiveexercisesapp.ui.components.WrongAnswerGame1
import com.example.cognitiveexercisesapp.ui.games.game1.Game1Screen
import com.example.cognitiveexercisesapp.ui.games.game2.Game2Screen
import com.example.cognitiveexercisesapp.ui.games.game3.Game3Screen
import com.example.cognitiveexercisesapp.ui.navigation.Instructions.Game1ScreenInstructions
import com.example.cognitiveexercisesapp.ui.navigation.HomeScreen
import com.example.cognitiveexercisesapp.ui.navigation.Instructions.Game2ScreenInstructions
import com.example.cognitiveexercisesapp.ui.navigation.Instructions.Game3ScreenInstructions
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import com.example.cognitiveexercisesapp.ui.theme.CognitiveExercisesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CognitiveExercisesAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.homeScreen) {
                    composable(Routes.homeScreen) {
                        HomeScreen(navController)
                    }
                    composable(Routes.wrongAnswer) {
                        WrongAnswerGame1(navController)
                    }
                    composable(Routes.exerciseFinished) {
                        ExerciseFinished(navController)
                    }
                    composable(Routes.game1ScreenInstructions) {
                        Game1ScreenInstructions(navController)
                    }
                    composable(Routes.game1Screen) {
                        Game1Screen(navController)
                    }
                    composable(Routes.game2ScreenInstructions) {
                        Game2ScreenInstructions(navController)
                    }
                    composable(Routes.game2Screen) {
                        Game2Screen(navController)
                    }
                    composable(Routes.game3ScreenInstructions) {
                        Game3ScreenInstructions(navController)
                    }
                    composable(Routes.game3Screen) {
                        Game3Screen(navController)
                    }
                }
            }
        }
    }
}
