package com.example.cognitiveexercisesapp.ui.games.game2

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cognitiveexercisesapp.ui.theme.AppTheme

@Composable
fun Game2Screen (navController: NavController) {
    Column(modifier = Modifier.then(AppTheme.screenPadding)) {
        Text(text = "Game 2", fontSize = AppTheme.h1)
    }

}