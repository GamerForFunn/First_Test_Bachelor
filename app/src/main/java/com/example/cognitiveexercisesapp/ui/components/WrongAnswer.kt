package com.example.cognitiveexercisesapp.ui.components

import android.os.Process
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cognitiveexercisesapp.R
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import com.example.cognitiveexercisesapp.ui.theme.CognitiveExercisesAppTheme

@Composable
fun WrongAnswerScreen(navController: NavController, currentGame: String) {
    var showContent by remember { mutableStateOf(true) }
    if (!showContent) {
        navController.navigate(Routes.exerciseFinished)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundTheme(modifier = Modifier.matchParentSize())

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top spacer to push the Oops! content further down from the top.
            Spacer(modifier = Modifier.weight(0.25f))

            // Oops! image and text section.
            EmbarrassedImage()

            // Spacer between top and middle sections.
            Spacer(modifier = Modifier.weight(0.05f))

            // Middle section: the confirmation box.
            AreYouSure()

            // Spacer between middle and bottom sections.
            Spacer(modifier = Modifier.weight(0.15f))

            // Bottom section: Exit and Retry buttons in a Row, with Note text below.
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ExitButton(onClick = { Process.killProcess(Process.myPid()) })
                RetryButton(onClick = {
                    when (currentGame) {
                        "Game1" -> navController.navigate(Routes.game1Screen)
                        "Game2" -> navController.navigate(Routes.game2Screen)
                        "Game3" -> navController.navigate(Routes.game3Screen)
                    }
                })
            }
            // Note text below the buttons.
            NoteText(modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 8.dp))

            // Bottom spacer to keep the bottom section from sitting too low.
            Spacer(modifier = Modifier.weight(0.2f))
        }
    }
}


@Composable
fun BackgroundTheme(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFFFFFFFF),
                        Color(0xffbbebf0),
                        Color(0xFFFFFFFF),
                        Color(0xFFFFFFFF)
                    )
                )
            )
    )
}

@Composable
fun ExitButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    // A simple button without a full-size Box.
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBECCFF)),
        modifier = modifier
            .width(100.dp)
            .height(50.dp)
    ) {
        Text(
            text = "Exit",
            fontSize = 30.sp,
            color = Color(0xFF007AFF)
        )
    }
}

@Composable
fun RetryButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF)),
        modifier = modifier
            .width(250.dp)
            .height(50.dp)
    ) {
        Text(
            text = "Retry",
            fontSize = 30.sp,
            color = Color.White
        )
    }
}

@Composable
fun NoteText(modifier: Modifier = Modifier) {
    Text(
        text = "Note: If you quit the game before completing it, you will not receive any rewards.",
        fontSize = 20.sp,
        lineHeight = 24.sp,
        color = Color(0xFF6B6B6B),
        textAlign = TextAlign.Center,
        modifier = modifier.padding(top = 8.dp)
    )
}

@Composable
fun AreYouSure(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(360.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .border(2.dp, Color.Blue, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "You can try this level again, or exit the exercise.\nWhat would you like to do?",
            fontSize = 24.sp,
            lineHeight = 36.sp,
            color = Color(0xFF6B6B6B)
        )
    }
}

@Composable
fun EmbarrassedImage(modifier: Modifier = Modifier) {
    // Combine image and text into one column.
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.embarrassed),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Oops!\nYou selected the wrong answer.",
            fontSize = 32.sp,
            lineHeight = 40.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WrongAnswerPreview() {
    val navController = rememberNavController()
    CognitiveExercisesAppTheme {
        WrongAnswerScreen(
            navController = navController,
            currentGame = "Game1"
        )
    }
}

