package com.example.cognitiveexercisesapp

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cognitiveexercisesapp.ui.components.ComparisonChartScreen
import com.example.cognitiveexercisesapp.ui.components.DoctorsCommentShowScreen
import com.example.cognitiveexercisesapp.ui.components.ExerciseFinished
import com.example.cognitiveexercisesapp.ui.components.WrongAnswerScreen
import com.example.cognitiveexercisesapp.ui.games.game1.Game1Screen
import com.example.cognitiveexercisesapp.ui.games.game2.Game2Screen
import com.example.cognitiveexercisesapp.ui.games.game3.Game3Screen
import com.example.cognitiveexercisesapp.ui.navigation.HomeScreen
import com.example.cognitiveexercisesapp.ui.navigation.Instructions.Game1ScreenInstructions
import com.example.cognitiveexercisesapp.ui.navigation.Instructions.Game2ScreenInstructions
import com.example.cognitiveexercisesapp.ui.navigation.Instructions.Game3ScreenInstructions
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import com.example.cognitiveexercisesapp.ui.notification.NotificationScheduler
import com.example.cognitiveexercisesapp.ui.notification.Notifier
import com.example.cognitiveexercisesapp.ui.theme.CognitiveExercisesAppTheme

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val notifierObj = Notifier(this)

        // Prompts the user for both normal notifications and pop-up notification.
        notifierObj.promptNotificationPermission(this)
        notifierObj.promptSystemNotificationPermission(this)

        /* This is responsible for the periodic notification that will be sent when the app is not
        * open. According to the Android WorkManager documentation, the minimum cycle length
        * is 15 minutes. */
        NotificationScheduler.schedulePeriodicNotification(this)

        /* This notification is only for testing the actual notification design wise, txt etc.
        * Will be remover or commented out later. */
        NotificationScheduler.scheduleTestNotification(this, 3_000)

        enableEdgeToEdge()
        setContent {
            CognitiveExercisesAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.homeScreen) {
                    composable(Routes.homeScreen) {
                        HomeScreen(navController)
                    }
                    composable(Routes.wrongAnswer + "/{currentGame}") {
                        val currentGame = it.arguments?.getString("currentGame")
                        WrongAnswerScreen(navController, currentGame ?: "No Game")
                    }
                    composable(Routes.comparisonChart) {
                        ComparisonChartScreen(navController)
                    }
                    composable(Routes.exerciseFinished) {
                        ExerciseFinished(navController)
                    }
                    composable(Routes.doctorsComment) {
                        DoctorsCommentShowScreen(navController)
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
