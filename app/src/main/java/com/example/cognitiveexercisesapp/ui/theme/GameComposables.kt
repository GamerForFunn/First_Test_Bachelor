package com.example.cognitiveexercisesapp.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/* This composable might not me neaded TODO*/
@Composable
fun CorrectAnswer () {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            fontSize = 32.sp,
            text = "Correct Answer",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 45.dp)
                .width(260.dp)
        )
    }
}



@Composable
fun WrongAnswer() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                fontSize = 32.sp,
                text = "Oops!\nYou Selected the\nwrong answer.",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 20.dp) // Add some space between the text and the buttons
            )
            Row(
                modifier = Modifier
                    .padding(top = 20.dp)
            ) {
                Button(
                    onClick = {}
                ) {
                    Text(
                        text = "Exit",
                        fontSize = 24.sp
                    )
                }
                Spacer(modifier = Modifier.width(10.dp)) // Add some space between the buttons
                Button(
                    onClick = {}
                ) {
                    Text(
                        text = "Retry",
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}