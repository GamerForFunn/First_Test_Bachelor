package com.example.main.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WordPairingWordButton(
    word: String,
    index: Int,
    isSelected: Boolean,
    isCorrectPair: Boolean?,
    onWordSelected: (String, Int) -> Unit
) {
    val backgroundColor = when {
        // Check if the button is selected FIRST
        isSelected && isCorrectPair == true -> Color.Green
        isSelected && isCorrectPair == false -> Color.Red
        isSelected -> Color.Gray // Single selected button
        else -> Color.White // Default
    }

    Button(
        onClick = { onWordSelected(word, index) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        )
    ) {
        Text(text = word, color = Color.Black)
    }
}