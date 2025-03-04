package com.example.cognitiveexercisesapp.ui.navigation.Instructions

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cognitiveexercisesapp.ui.data.GameInstructions
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import com.example.cognitiveexercisesapp.ui.theme.AppTheme

@Composable
fun Game3ScreenInstructions(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .then(AppTheme.screenPadding)
    ) {
        Text(text = GameInstructions.getGame3Instructions(), fontSize = AppTheme.h1)

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.navigate(Routes.game3Screen) }) {
            Text(text = "Start", fontSize = AppTheme.buttonTextSize)
        }
    }
}