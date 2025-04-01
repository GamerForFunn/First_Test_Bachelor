package com.example.cognitiveexercisesapp.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cognitiveexercisesapp.ui.components.ComposeOverlayService
import com.example.cognitiveexercisesapp.ui.data.GameInstructions
import com.example.cognitiveexercisesapp.ui.theme.AppTheme
import com.example.cognitiveexercisesapp.ui.theme.CognitiveExercisesAppTheme
import com.example.cognitiveexercisesapp.ui.components.PopupDialog

// Checks if the "Activate pop-up" button has been pressed.
var popUpActive = false

@Composable
fun HomeScreen(navController: NavController) {
    var currentLanguage by remember { mutableStateOf(GameInstructions.currentLanguage) }

    var difficulty by remember { mutableStateOf(GameInstructions.difficulty) }
    var showPopup by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Cognitive training\nexercises", fontSize = AppTheme.h1)

        Spacer(modifier = Modifier.height(16.dp))

        // Difficulty Slider
        Text(text = "Select Difficulty: ${difficulty.toInt()}", fontSize = AppTheme.h2)
        Slider(
            value = difficulty,
            onValueChange = { newValue ->
                difficulty = newValue
                GameInstructions.difficulty = newValue
            },
            valueRange = 1f..100f,
            steps = 99, // 1 - 100
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        // **Language Selection**
        if (false) {
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
                Spacer(modifier = Modifier.height(16.dp))
            }

        } else {
            Spacer(modifier = Modifier.height(80.dp))
        }


        Button(onClick = { navController.navigate(Routes.game1ScreenInstructions) }, modifier = Modifier.padding(8.dp)) {
            Text(text = "Game 1", fontSize = AppTheme.buttonTextSize)
        }
        Button(onClick = { navController.navigate(Routes.game2ScreenInstructions) }, modifier = Modifier.padding(8.dp)) {
            Text(text = "Game 2", fontSize = AppTheme.buttonTextSize)
        }
        Button(onClick = { navController.navigate(Routes.game3ScreenInstructions) }, modifier = Modifier.padding(8.dp)) {
            Text(text = "Game 3", fontSize = AppTheme.buttonTextSize)
        }
        val context = LocalContext.current.applicationContext
        if (!popUpActive) {
            Button(
                onClick = { ComposeOverlayService.showOverlay(context)
                          popUpActive = true
                          showPopup = true},
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Activate pop-up", fontSize = AppTheme.buttonTextSize)
            }
            if (showPopup) {
                PopupDialog(onDismiss = { showPopup = false })
            }
        }
    }
}
