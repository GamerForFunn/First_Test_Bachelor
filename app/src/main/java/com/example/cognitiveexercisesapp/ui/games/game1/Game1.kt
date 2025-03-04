package com.example.cognitiveexercisesapp.ui.games.game1

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import com.example.cognitiveexercisesapp.ui.theme.AppTheme

@Composable
fun Game1Screen (navController: NavController) {
    Column(modifier = Modifier.then(AppTheme.screenPadding)) {
        Text(text = "Game 1", fontSize = AppTheme.h1)
        Button(
            onClick = { navController.navigate(Routes.wrongAnswer) }
        ) {
            Text(text = "wrong answer test")
        }
    }

}