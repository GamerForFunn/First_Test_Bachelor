package com.example.cognitiveexercisesapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.cognitiveexercisesapp.R
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import com.example.cognitiveexercisesapp.ui.theme.AppTheme
import kotlinx.coroutines.delay


// This actually displays the score, time, wrong answers etc.
@Composable
fun ExerciseFinished(navController: NavController) {

    var showExerciseFinishedScreen by remember { mutableStateOf(true) }

    // Vars for showing the different parts of the screen after a given delay.
    // All of these are supposed to be false EXCEPT for showContent.
    var showTimeCounter by remember { mutableStateOf(false) }
    var showWrongAnswers by remember { mutableStateOf(false) }
    var showLevelDifficulty by remember { mutableStateOf(false) }
    var showTotalScore by remember { mutableStateOf(false) }
    var showUserScore by remember { mutableStateOf(false) }
    var showButton by remember { mutableStateOf(false) }
    var showContent by remember { mutableStateOf(true) }


    // Val for deciding how long to delay between each part of the screen in milliseconds.
    val delayTime = 500L

    // Delay for showing the different parts of the screen.
    LaunchedEffect(Unit) {
        delay(delayTime)
        showTimeCounter = true
        delay(delayTime)
        showWrongAnswers = true
        delay(delayTime)
        showLevelDifficulty = true
        delay(delayTime)
        showTotalScore = true
        showUserScore = true
        delay(delayTime)
        showButton = true
    }
    // The different parts of the screen are displayed after a delay.
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (showContent) {
            BackgroundThemeExerciseFinished(
                modifier = Modifier.padding(innerPadding)
            )
            FinishedExercise(
                modifier = Modifier.padding(innerPadding)
            )
            if (showTimeCounter) {
                ShowTimeCounter(true, modifier = Modifier.padding(innerPadding))
            }
            // countWrongAnswers is temp until the system is up n running. Should take the
            // amount of wrong answers from the exercise.
            if (showWrongAnswers) {
                ShowWrongAnswers(
                    countWrongAnswers,
                    true,
                    modifier = Modifier.padding(innerPadding)
                )
            }
            // typeDifficulty and difficultyPoints are temp until the system is up n running.
            // Should take the difficulty from the exercise as well as a given difficulty bonus.
            if (showLevelDifficulty) {
                ShowLevelDifficulty(
                    typeDifficulty,
                    difficultyPoints,
                    true,
                    modifier = Modifier.padding(innerPadding)
                )
            }
            if (showTotalScore) {
                ShowTotalScore(true, modifier = Modifier.padding(innerPadding))
            }
            // This is just a ph for calculating the user score.
            if (showUserScore) {
                CalculateUserScore(
                    difficultyPoints,
                    timeSpentSeconds,
                    modifier = Modifier.padding(innerPadding)
                )
            }
            if (showButton) {
                ContinueButton(
                    onClick = {
                        showContent = false
                        showExerciseFinishedScreen = false
                    },
                    true,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        } else {
            navController.navigate(Routes.comparisonChart)
        }
    }
}

// Function that chooses background theme, color, etc.
@Composable
fun BackgroundThemeExerciseFinished(modifier: Modifier = Modifier) {
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


/* This is the start screen when you first open the app. This is just a placeholder for when it
* gets added to the other mini games. */
@Composable
fun StartScreen(onStartClicked: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onStartClicked) {
            Text("Start Exercise")
        }
    }
}

// This displays the exercise finished text.
@Composable
fun FinishedExercise(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Exercise finished",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(top = 100.dp) // Optional: add top padding if needed
                .width(270.dp)
        )
    }
}

// Temp val for testing.
private val timeSpentSeconds = 221

// Shows the counter (time used) in the app.
@Composable
fun ShowTimeCounter(imageVisibility: Boolean, modifier: Modifier = Modifier) {
    val imageIsVisible by remember { mutableStateOf(imageVisibility) }
    val seconds = timeSpentSeconds
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Time: $seconds",
            textAlign = TextAlign.Center,
            color = Color.Blue,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 400.dp)
                .width(250.dp)
        )
        if (imageIsVisible) {
            Image(
                painter = painterResource(id = R.drawable.confetti),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(bottom = 280.dp)
                    .padding(start = 18.dp)
                    .size(70.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.confetti),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 170.dp)
                    .padding(end = 18.dp)
                    .size(70.dp)
            )
        }
    }
}

// Only for testing. Delete afterwards.
private val countWrongAnswers = 3

// Displays the amount of wrong answers the user got.
@Composable
fun ShowWrongAnswers(
    wrongAnswers: Int,
    imageVisibility: Boolean,
    modifier: Modifier = Modifier
) {
    val imageIsVisible by remember { mutableStateOf(imageVisibility) }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Wrong Answers: $wrongAnswers",
            textAlign = TextAlign.Center,
            color = Color.Blue,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 340.dp)
                .width(250.dp)
        )
        if (imageIsVisible) {
            Image(
                painter = painterResource(id = R.drawable.confetti),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(50.dp)
                    .padding(top = 100.dp)
                    .size(70.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.confetti),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(50.dp)
                    .size(70.dp)
            )
        }
    }
}

// Both of these are temp for testing.
private val typeDifficulty = "Normal"
private val difficultyPoints = 200

// Displays the difficulty level of the level. Gives points based on difficulty.
@Composable
fun ShowLevelDifficulty(
    levelDifficulty: String,
    difficultyBonusPoints: Int,
    imageVisibility: Boolean,
    modifier: Modifier = Modifier
) {
    val imageIsVisible by remember { mutableStateOf(imageVisibility) }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Level $levelDifficulty: $difficultyBonusPoints",
            textAlign = TextAlign.Center,
            color = Color.Blue,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 280.dp)
                .width(250.dp)
        )
        if (imageIsVisible) {
            Image(
                painter = painterResource(id = R.drawable.confetti),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(50.dp)
                    .padding(top = 180.dp)
                    .size(70.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.confetti),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(20.dp)
                    .padding(bottom = 200.dp)
                    .size(70.dp)
            )
        }
    }
}

// This shows the total score that the player managed to achieve.
@Composable
fun ShowTotalScore(imageVisibility: Boolean, modifier: Modifier = Modifier) {
    val imageIsVisible by remember { mutableStateOf(imageVisibility) }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Your score", // PH until score system is up n going
            textAlign = TextAlign.Center,
            color = Color.Blue,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(bottom = 105.dp)
                .width(250.dp)
        )
        if (imageIsVisible) {
            Image(
                painter = painterResource(id = R.drawable.confetti),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(50.dp)
                    .size(70.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.confetti),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(50.dp)
                    .padding(top = 85.dp)
                    .size(70.dp)
            )
        }
    }
}

// This calculates the user score based on difficulty and time spent. Just a placeholder for now.
@Composable
fun CalculateUserScore(difficultyBonusPoints: Int, timeSpent: Int, modifier: Modifier = Modifier) {
    val userScore = difficultyBonusPoints - (timeSpent / 2)
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$userScore", // PH until score system is up n going
            textAlign = TextAlign.Center,
            color = Color.Blue,
            fontSize = 50.sp,
            modifier = Modifier
                .width(250.dp)
        )
    }
}

// Continue button that takes you to the next screen.
@Composable
fun ContinueButton(onClick: () -> Unit, showButton: Boolean, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(48.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        if (showButton) {
            Button(
                onClick = onClick,
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text(
                    "Continue",
                    fontSize = 24.sp,
                    modifier = Modifier
                )
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 4.dp)
                )
            }
        }
    }
}




/* TODO
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BachelorAppTheme {
        showScreenObj.MainScreen()
    }
}*/
