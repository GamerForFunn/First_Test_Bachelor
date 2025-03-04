package com.example.cognitiveexercisesapp.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cognitiveexercisesapp.ui.data.GameInstructions
import com.example.cognitiveexercisesapp.ui.theme.AppTheme

@Composable
fun HomeScreen(navController: NavController) {
    var currentLanguage by remember { mutableStateOf(GameInstructions.currentLanguage) }

    // ✅ Directly reference GameInstructions.difficulty so changes persist globally
    var difficulty by remember { mutableStateOf(GameInstructions.difficulty) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Cognitive training\nexercises", fontSize = AppTheme.h1)

        Spacer(modifier = Modifier.height(16.dp))

        // **Difficulty Slider**
        Text(text = "Select Difficulty: ${difficulty.toInt()}", fontSize = AppTheme.h2)
        Slider(
            value = difficulty,
            onValueChange = { newValue ->
                difficulty = newValue
                GameInstructions.difficulty = newValue // ✅ Save globally
            },
            valueRange = 1f..100f,
            steps = 99, // 1 - 100
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // **Language Selection**
        Text(text = "Select Language:", fontSize = AppTheme.h2)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            RadioButton(
                selected = currentLanguage == "EN",
                onClick = {
                    GameInstructions.currentLanguage = "EN"
                    currentLanguage = "EN"
                }
            )
            Text(text = "English", fontSize = AppTheme.buttonTextSize, modifier = Modifier.padding(start = 8.dp))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            RadioButton(
                selected = currentLanguage == "NO",
                onClick = {
                    GameInstructions.currentLanguage = "NO"
                    currentLanguage = "NO"
                }
            )
            Text(text = "Norsk", fontSize = AppTheme.buttonTextSize, modifier = Modifier.padding(start = 8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate(Routes.game1ScreenInstructions) }, modifier = Modifier.padding(8.dp)) {
            Text(text = "Game 1", fontSize = AppTheme.buttonTextSize)
        }
        Button(onClick = { navController.navigate(Routes.game2ScreenInstructions) }, modifier = Modifier.padding(8.dp)) {
            Text(text = "Game 2", fontSize = AppTheme.buttonTextSize)
        }
        Button(onClick = { navController.navigate(Routes.game3ScreenInstructions) }, modifier = Modifier.padding(8.dp)) {
            Text(text = "Game 3", fontSize = AppTheme.buttonTextSize)
        }
    }
}
