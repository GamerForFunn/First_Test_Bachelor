package com.example.cognitiveexercisesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

    @Composable
    fun PopupContent(onClose: () -> Unit) {
        // Column replaces LinearLayout with vertical orientation.
        Column(
            modifier = Modifier
                .background(Color.White) // Background white (#FFFFFF)
                .padding(16.dp) // Padding of 16dp
                .wrapContentSize() // Sizes to content
        ) {
            // Text element similar to TextView
            Text(
                text = "This is a popup",
                fontSize = 16.sp,
                color = Color.Black // Text color black (#000000)
            )

            // Spacer to provide 12dp space (like layout_marginTop for the button)
            Spacer(modifier = Modifier.height(12.dp))

            // Box centers the button horizontally, replicating layout_gravity="center"
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    onClick = onClose,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFDC5F00) // Button background tint (#DC5F00)
                    )
                ) {
                    Text(
                        text = "Close",
                        color = Color.White // Button text color white (#FFFFFF)
                    )
                }
            }
        }
    }

    @Composable
    fun PopupDialog(onDismiss: () -> Unit) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.White
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "This is a popup",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC5F00))                    ) {
                        Text(text = "Close", color = Color.White)
                    }
                }
            }
        }
    }


