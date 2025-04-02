package com.example.cognitiveexercisesapp.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cognitiveexercisesapp.R
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import com.example.cognitiveexercisesapp.ui.theme.CognitiveExercisesAppTheme
import com.example.cognitiveexercisesapp.ui.theme.whiteTextStyle
import kotlin.math.round


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
                averageScore = 500,
                modifier = Modifier.padding(innerPadding)
            )
            ContinueButton(
                onClick = {
                    navController.navigate(Routes.homeScreen)
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
            .height(140.dp)
            .shadow(3.dp)
            .background(Color(0xFF40376E), shape = RoundedCornerShape(10.dp))
    ) {
        Text(
            text = "Poengdiagram",
            textAlign = TextAlign.Center,
            style = whiteTextStyle.copy(fontSize = 32.sp),
            modifier = Modifier
                .padding(top = 75.dp)
                .width(320.dp)
        )

    }
}

// Function that is responsible for the "Your score" part. Takes in the user score as argument.
@Composable
fun ScoreDisplay(userScore: Int, averageScore: Int, modifier: Modifier = Modifier) {
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
                text = "Dine poeng",
                textAlign = TextAlign.Center,
                style = whiteTextStyle.copy(fontSize = 32.sp),
                modifier = Modifier
                    .padding(bottom = 65.dp)
                    .width(260.dp)
            )
        }
Box(
    modifier = modifier
        .padding(bottom = 380.dp)
        .animateContentSize()
) {
    // The score itself. Takes it in as argument from a score calculating function.
    Text(
        text = "$userScore",
        textAlign = TextAlign.Center,
        style = whiteTextStyle.copy(fontSize = 32.sp),
        modifier = Modifier
            .padding(top = 95.dp)
            .width(260.dp)
    )
    // Animated rectangle representing the user score
    Box(
        modifier = Modifier
            .padding(top = 150.dp)
            .width(260.dp)
            .height(60.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
    ) {
        val expanded by remember { mutableStateOf(true) }
        Box(
            modifier = Modifier
                .background(Color(0xFFB23259), shape = RoundedCornerShape(10.dp))
                .animateContentSize(animationSpec = tween(durationMillis = 2000))
                .width(if (expanded)(userScore / 500f * 260).dp else (0).dp)
                .fillMaxHeight()
        )
    }
}
            // "Average score" box + info
        Box(
            modifier = modifier
        ) {
            Text(
                text = "Snittpoeng",
                textAlign = TextAlign.Center,
                style = whiteTextStyle.copy(fontSize = 32.sp),
                modifier = Modifier
                    .padding(top = 45.dp)
                    .width(260.dp)
            )
            // Avg. score. PH as comparison between users is not a priority.
            Text(
                text = "$averageScore",
                textAlign = TextAlign.Center,
                style = whiteTextStyle.copy(fontSize = 32.sp),
                modifier = Modifier
                    .padding(top = 95.dp)
                    .width(260.dp)
            )
            // Animated rectangle representing the user score
            Box(
                modifier = Modifier
                    .padding(top = 150.dp)
                    .width(260.dp)
                    .height(60.dp)
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
            ) {
                val expanded by remember { mutableStateOf(true) }
                Box(
                    modifier = Modifier
                        .background(Color(0xFFB23259), shape = RoundedCornerShape(10.dp))
                        .animateContentSize(animationSpec = tween(durationMillis = 2000))
                        .width(if (expanded)(averageScore / 500f * 260).dp else (0).dp)
                        .fillMaxHeight()
                )
            }
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
                            .padding(top = 160.dp)
                            .width(50.dp)
                            .height(40.dp)
                    )
                    Text(
                        text = "Andre spillere fikk i gjennomsnitt " +
                                "$averageScore poeng",
                        style = whiteTextStyle.copy(fontSize = 18.sp),
                        modifier = Modifier
                            .padding(top = 160.dp)
                            .width(330.dp)
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


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    CognitiveExercisesAppTheme {
        ComparisonChartScreen(navController = navController)
    }
}
