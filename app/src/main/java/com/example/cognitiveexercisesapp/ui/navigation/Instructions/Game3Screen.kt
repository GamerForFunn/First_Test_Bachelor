package com.example.cognitiveexercisesapp.ui.navigation.Instructions

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cognitiveexercisesapp.ui.data.GameInstructions
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import com.example.cognitiveexercisesapp.ui.theme.AppTheme

@Composable
fun Game3ScreenInstructions(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .then(AppTheme.screenPadding)
    ) {
        Text(
            text = "Par sokkene! 🧦",
            fontSize = AppTheme.h1,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = GameInstructions.getGame3Instructions(), fontSize = 26.sp)

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedButton(
                onClick = { navController.navigate(Routes.homeScreen) }
            ) {
                Text(text = "Avbryt", fontSize = 30.sp)
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = { navController.navigate(Routes.game3Screen) },
                modifier = Modifier
                    .fillMaxWidth()
                ) {
                Text(text = "Start", fontSize = 30.sp)
            }
        }
    }
}