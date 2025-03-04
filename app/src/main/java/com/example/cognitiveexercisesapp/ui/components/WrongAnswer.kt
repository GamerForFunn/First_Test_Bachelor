package com.example.cognitiveexercisesapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
fun WrongAnswerGame1(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = GameInstructions.getWronAnswerGame1(), fontSize = AppTheme.h1)
        Row {
            Button(
                onClick = { navController.navigate(Routes.homeScreen) },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Exit")
            }
            Button(
                onClick = { navController.navigate(Routes.game1Screen) },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Try again")
            }
        }
    }
}
