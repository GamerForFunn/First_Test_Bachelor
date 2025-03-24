package com.example.cognitiveexercisesapp.ui.notification

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay

class SystemPopUp {
    companion object {
        @Composable
        fun Display(onRedirect: () -> Unit) {
            val showDialog = remember { mutableStateOf(false) }

            // Wait for 10 seconds before showing the popup
            LaunchedEffect(Unit) {
                delay(10000)
                showDialog.value = true
            }

            if (showDialog.value) {
                AlertDialog(
                    onDismissRequest = { showDialog.value = false },
                    title = { Text("Permission Granted") },
                    text = { Text("Your system notification permission has been granted. Click below to return to the app.") },
                    confirmButton = {
                        Button(onClick = {
                            showDialog.value = false
                            onRedirect()
                        }) {
                            Text("Return to App")
                        }
                    }
                )
            }
        }
    }
}
