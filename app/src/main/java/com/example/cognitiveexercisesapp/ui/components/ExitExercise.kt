package com.example.cognitiveexercisesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ExitExerciseScreen {

    @Composable
    fun ExitExerciseShowScreen() {
        var showContent by remember { mutableStateOf(true) }
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            if (showContent) {
                BackgroundTheme(
                    modifier = Modifier.padding(innerPadding)
                )
                NoteText(
                    modifier = Modifier.padding(innerPadding)
                )
                AreYouSure(
                    modifier = Modifier.padding(innerPadding)
                )
                YesButton(
                onClick = { android.os.Process.killProcess(android.os.Process.myPid()) }
                )
                NoButton(
                    onClick = { showContent = false },
                    modifier = Modifier.padding(innerPadding)
                )
            } else {
                exitExerciseObj.ExitExerciseShowScreen()
            }
        }
    }

    // Function that chooses background theme, color, etc.
    @Composable
    fun BackgroundTheme(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFFFFFFFF), Color(0xffbbebf0), Color(0xFFFFFFFF),
                            Color(0xFFFFFFFF)
                        )
                    )
                )
        )
    }

    @Composable
    fun YesButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .padding(10.dp)
                .padding(top = 130.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBECCFF)),
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
            ) {
                Text(
                    "Yes",
                    fontSize = 30.sp,
                    color = Color(0xFF007AFF),
                    modifier = Modifier
                )
            }
        }
    }

    @Composable
    fun NoButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .padding(15.dp)
                .padding(top = 130.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF)),
                modifier = Modifier
                    .width(250.dp)
                    .height(50.dp)
            ) {
                Text(
                    "No",
                    fontSize = 30.sp,
                    modifier = Modifier
                )
            }
        }
    }

    @Composable
    fun NoteText(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .padding(40.dp)
                .padding(bottom = 120.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                text = "Note: If you quit the game before completing it, you will not receive any rewards.",
                fontSize = 20.sp,
                color = Color(0xFF6B6B6B),
                modifier = Modifier
            )
        }
    }

    @Composable
    fun AreYouSure(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .padding(40.dp)
                .padding(top = 310.dp)
                .width(320.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .border(2.dp, Color.Blue, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Do you really want to exit the exercise? ",
                fontSize = 25.sp,
                color = Color(0xFF6B6B6B),
                modifier = Modifier.padding(16.dp)
            )
        }
    }

}

val exitExerciseObj = ExitExerciseScreen()

/* TODO
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BachelorAppTheme {
        exitExerciseObj.ExitExerciseShowScreen()
    }
}*/
