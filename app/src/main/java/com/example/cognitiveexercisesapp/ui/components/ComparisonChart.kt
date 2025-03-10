package com.example.cognitiveexercisesapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cognitiveexercisesapp.R
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import com.example.cognitiveexercisesapp.ui.theme.whiteTextStyle


// Function that shows the screen.
@Composable
fun ComparisonChartScreen(navController: NavController) {
    var showContent by remember { mutableStateOf(true) }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (showContent) {
            BackgroundThemeComparisonChart(
                modifier = Modifier.padding(innerPadding)
            )
            ScreenTitle()
            ScoreDisplay(
                // The user score here should be calculated from the exercise. This value is just ph.
                userScore = 436,
                modifier = Modifier.padding(innerPadding)
            )
            ContinueButton(
                onClick = {
                    navController.navigate(Routes.doctorsComment)
                          },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

// Function that chooses background theme, color, etc.
@Composable
private fun BackgroundThemeComparisonChart(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Color(0xFF40376E)
            )
    )
}

// Function that is responsible for the screen title.
@Composable
fun ScreenTitle() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp)
            .height(117.dp)
            .shadow(1.dp)
    ) {
        Text(
            text = "Comparison chart",
            textAlign = TextAlign.Center,
            style = whiteTextStyle.copy(fontSize = 32.sp),
            modifier = Modifier
                .padding(top = 45.dp)
                .width(320.dp)
        )

    }
}

// Function that is responsible for the "Your score" part. Takes in the user score as argument.
@Composable
fun ScoreDisplay(userScore: Int, modifier: Modifier = Modifier) {
        Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
            // "Your score" box + info
        Box(
            modifier = modifier
                .padding(bottom = 380.dp)
        ) {
            Text(
                text = "Din score",
                textAlign = TextAlign.Center,
                style = whiteTextStyle.copy(fontSize = 32.sp),
                modifier = Modifier
                    .padding(top = 45.dp)
                    .width(260.dp)
            )
            // The score itself. Takes it in as argument from a score calculating function.
            Text(
                text = "$userScore",
                textAlign = TextAlign.Center,
                style = whiteTextStyle.copy(fontSize = 32.sp),
                modifier = Modifier
                    .padding(top = 95.dp)
                    .width(260.dp)
            )
        }
            // "Average score" box + info
        Box(
            modifier = modifier
        ) {
            Text(
                text = "Gjennomsnitt score",
                textAlign = TextAlign.Center,
                style = whiteTextStyle.copy(fontSize = 32.sp),
                modifier = Modifier
                    .padding(top = 45.dp)
                    .width(260.dp)
            )
            // Avg. score. PH as comparison between users is not a priority.
            Text(
                text = "$userScore",
                textAlign = TextAlign.Center,
                style = whiteTextStyle.copy(fontSize = 32.sp),
                modifier = Modifier
                    .padding(top = 95.dp)
                    .width(260.dp)
            )
        }
            // Info point about average user score.
            Box(
                modifier = modifier
                    .padding(top = 170.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = modifier
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.info_point),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(top = 95.dp)
                            .width(50.dp)
                    )
                    Text(
                        text = "Andre spillere får gjennomsnittlig " +
                                "$userScore poeng i denne oppgaven",
                        style = whiteTextStyle.copy(fontSize = 12.sp),
                        modifier = Modifier
                            .padding(top = 95.dp)
                            .width(260.dp)
                    )
                }
            }
    }
}

@Composable
fun ContinueButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(48.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
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

/*
val comparisonChartObj = ComparisonChartScreen()

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BachelorAppTheme {
        comparisonChartObj.ComparisonChartShowScreen()
    }
}*/
