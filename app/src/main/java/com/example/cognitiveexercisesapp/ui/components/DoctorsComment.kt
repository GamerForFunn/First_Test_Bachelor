package com.example.cognitiveexercisesapp.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
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



@Composable
fun DoctorsCommentShowScreen(navController: NavController) {
    val showContent by remember { mutableStateOf(true) }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (showContent) {
            BackgroundThemeDoctorsComment(
                modifier = Modifier.padding(innerPadding)
            )
            ScreenTitleDoctorsComment()
            ScoreDisplayDoctorsComment(
                // The user score here should be calculated from the exercise. This value is just ph.
                userScore = 436,
                modifier = Modifier.padding(innerPadding)
            )
            ContinueButtonDoctorsComment(
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
fun BackgroundThemeDoctorsComment(modifier: Modifier = Modifier) {
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
fun ScreenTitleDoctorsComment() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp)
            .height(140.dp)
            .shadow(1.dp)
    ) {
        Text(
            text = "The doctor's comment",
            textAlign = TextAlign.Center,
            style = whiteTextStyle.copy(fontSize = 28.sp),
            modifier = Modifier
                .padding(top = 75.dp)
                .width(380.dp)
        )

    }
}

// Function that is responsible for the "Your score" part. Takes in the user score as argument.
@Composable
fun ScoreDisplayDoctorsComment(userScore: Int, modifier: Modifier = Modifier) {
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
                text = "Your score",
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
        // Box for the doctor's comment image.
        Box(
            modifier = modifier
                .padding(top = 250.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.doctors_comment),
                contentDescription = null,
                modifier = Modifier
                    .size(350.dp)
            )
        }
    }
}

@Composable
fun ContinueButtonDoctorsComment(onClick: () -> Unit, modifier: Modifier = Modifier) {
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
                "Continue",
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
fun DoctorsCommentPreview() {
    val navController = rememberNavController()
    CognitiveExercisesAppTheme {
        DoctorsCommentShowScreen(navController = navController)
    }
}
