package com.example.cognitiveexercisesapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cognitiveexercisesapp.ui.data.GameInstructions
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import com.example.cognitiveexercisesapp.ui.theme.AppTheme

@Composable
fun WrongAnswerGame1(navController: NavController) {
    Column(
        modifier = Modifier
            .then(AppTheme.screenPadding)
            .fillMaxSize()
    ) {
        Text(text = GameInstructions.getWronAnswerGame1(), fontSize = AppTheme.h1)
        Button(
            onClick = { navController.navigate(Routes.homeScreen) }
        ) {
            Text(text = "Exit")
        }
        Button(
            onClick = { navController.navigate(Routes.game1Screen) }
        ) {
            Text(text = "Try again")
        }
    }
}
