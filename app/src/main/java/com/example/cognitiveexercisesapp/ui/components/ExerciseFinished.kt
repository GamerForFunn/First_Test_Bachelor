package com.example.cognitiveexercisesapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.navigation.compose.rememberNavController
import com.example.cognitiveexercisesapp.R
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import com.example.cognitiveexercisesapp.ui.theme.CognitiveExercisesAppTheme
import com.example.cognitiveexercisesapp.ui.theme.whiteTextStyle
import kotlinx.coroutines.delay

// This actually displays the score, time, wrong answers etc.
@Composable
fun ExerciseFinished(navController: NavController) {

    // Vars for showing the different parts of the screen after a given delay.
    // All of these are supposed to be false EXCEPT for showContent.
    var showTimeCounter by remember { mutableStateOf(false) }
    var showWrongAnswers by remember { mutableStateOf(false) }
    var showLevelDifficulty by remember { mutableStateOf(false) }
    var showTotalScore by remember { mutableStateOf(false) }
    var showUserScore by remember { mutableStateOf(false) }
    var showButton by remember { mutableStateOf(false) }

    // Supposed to count the number of confetti images that are displayed. When all are shown,
    // it will hide them again and show the sparkling images instead.
    var imageCounter = 0

    // Val for deciding how long to delay between each part of the screen in milliseconds.
    val delayTime = 750L

    // Delay for showing the different parts of the screen.
    LaunchedEffect(Unit) {
        delay(delayTime)
        showTimeCounter = true
        imageCounter++
        delay(delayTime)
        showWrongAnswers = true
        imageCounter++
        delay(delayTime)
        showLevelDifficulty = true
        imageCounter++
        delay(delayTime)
        showTotalScore = true
        imageCounter++
        showUserScore = true
        imageCounter++
        delay(delayTime)
        if (imageCounter == 5) {
        showButton = true
        }
    }
    // The different parts of the screen are displayed after a delay.
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (true) {
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
                        navController.navigate(Routes.comparisonChart)
                    },
                    true,
                    modifier = Modifier.padding(innerPadding)
                )
            }
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

// This displays the exercise finished text.
@Composable
fun FinishedExercise(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Spill fullført",
            textAlign = TextAlign.Center,
            style = whiteTextStyle.copy(fontSize = 32.sp),
            fontSize = 30.sp,
            color = Color(0xFF40376E),
            modifier = Modifier
                .padding(top = 100.dp) 
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
            text = "Tid: $seconds",
            textAlign = TextAlign.Center,
            style = whiteTextStyle.copy(fontSize = 32.sp),
            color = Color(0xFF007AFF),
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
            text = "Feil svar: $wrongAnswers",
            textAlign = TextAlign.Center,
            color = Color(0xFF007AFF),
            style = whiteTextStyle.copy(fontSize = 32.sp),
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
            color = Color(0xFF007AFF),
            style = whiteTextStyle.copy(fontSize = 32.sp),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 220.dp)
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
            text = "Dine poeng", // PH until score system is up n going
            textAlign = TextAlign.Center,
            color = Color(0xFF007AFF),
            style = whiteTextStyle.copy(fontSize = 32.sp),
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
                    .padding(top = 95.dp)
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
            color = Color(0xFF007AFF),
            style = whiteTextStyle.copy(fontSize = 32.sp),
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
                    .height(50.dp),
                colors = ButtonColors(Color(0xFF007AFF),Color(0xFF007AFF),Color(0xFF007AFF),Color(0xFF007AFF))
            ) {
                Text(
                    "Fortsett",
                    fontSize = 24.sp,
                    modifier = Modifier,
                    color = Color.White
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




@Preview(showBackground = true)
@Composable
fun ExerciseFinishedPreview() {
    val navController = rememberNavController()
    CognitiveExercisesAppTheme {
        ExerciseFinished(
            navController = navController
        )
    }
}