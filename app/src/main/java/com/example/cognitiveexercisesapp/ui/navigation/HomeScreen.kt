package com.example.cognitiveexercisesapp.ui.navigation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cognitiveexercisesapp.ui.components.ComposeOverlayService
import com.example.cognitiveexercisesapp.ui.components.PermissionStatusItem
import com.example.cognitiveexercisesapp.ui.data.GameInstructions
import com.example.cognitiveexercisesapp.ui.theme.AppTheme
import com.example.cognitiveexercisesapp.ui.theme.CognitiveExercisesAppTheme
import com.example.cognitiveexercisesapp.ui.components.PopupDialog
import com.example.cognitiveexercisesapp.utils.PermissionUtils

// Checks if the "Activate pop-up" button has been pressed.
var popUpActive = false

@Composable
fun HomeScreen(navController: NavController) {
    var currentLanguage by remember { mutableStateOf(GameInstructions.currentLanguage) }

    var difficulty by remember { mutableStateOf(GameInstructions.difficulty) }
    var showPopup by remember { mutableStateOf(false) }

    // Permissions
    val context = LocalContext.current
    var overlayPermissionGranted by remember { mutableStateOf(false) }
    var usageStatsPermissionGranted by remember { mutableStateOf(false) }

    // Function to update permission states
    fun updatePermissionStates() {
        overlayPermissionGranted = PermissionUtils.canDrawOverlays(context)
        usageStatsPermissionGranted = PermissionUtils.canAccessUsageStats(context)
    }

    LaunchedEffect(Unit) {
        updatePermissionStates()
    }

    // Check permissions when returning from settings
    DisposableEffect(Unit) {
        val activity = context as? ComponentActivity
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                updatePermissionStates()
            }
        }

        activity?.lifecycle?.addObserver(lifecycleObserver)

        onDispose {
            activity?.lifecycle?.removeObserver(lifecycleObserver)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
    ) {
        Text(text = "Hjernetrim", fontSize = AppTheme.h1)

        Spacer(modifier = Modifier.height(16.dp))

        // Difficulty Slider
        Text(text = "Velg vanskelighetsgrad: ${difficulty.toInt()}", fontSize = AppTheme.h2)
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
        }

        // Activate pop-up button (commented out for now)
        /*
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
         */

        // Permission Status Section
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Tillatelser",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "For å kunne bruke appen må begge tillatelser være aktivert.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Permission Status Indicators
                PermissionStatusItem(
                    title = "Vis over andre apper",
                    isGranted = overlayPermissionGranted,
                    onRequest = {
                        PermissionUtils.navigateToOverlaySettings(context)
                    }
                )

                Spacer(modifier = Modifier.height(4.dp))

                PermissionStatusItem(
                    title = "Brukstilgang",
                    isGranted = usageStatsPermissionGranted,
                    onRequest = {
                        PermissionUtils.navigateToUsageSettings(context)
                    }
                )
            }
        }

        // Service Status
        if (overlayPermissionGranted && usageStatsPermissionGranted) {
            Text(
                text = "Appen er klar til bruk!",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
