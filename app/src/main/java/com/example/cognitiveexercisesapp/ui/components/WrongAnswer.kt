package com.example.cognitiveexercisesapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cognitiveexercisesapp.R
import com.example.cognitiveexercisesapp.ui.navigation.Routes


@Composable
fun WrongAnswerScreen(navController: NavController,currentGame: String) {
    var showContent by remember { mutableStateOf(true) }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (showContent) {
            BackgroundTheme(
                modifier = Modifier.padding(innerPadding)
            )
            EmbarrassedImage(
                modifier = Modifier.padding(innerPadding)
            )
            NoteText(
                modifier = Modifier.padding(innerPadding)
            )
            AreYouSure(
                modifier = Modifier.padding(innerPadding)
            )
            ExitButton(
                onClick = { android.os.Process.killProcess(android.os.Process.myPid()) }
            )
            RetryButton(/*todo*/
                onClick = {
                    when (currentGame) {
                        "Game1" -> navController.navigate(Routes.game1Screen)
                        "Game2" -> navController.navigate(Routes.game2Screen)
                        "Game3" -> navController.navigate(Routes.game3Screen)
                    }
                },
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            navController.navigate(Routes.exerciseFinished)
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
fun ExitButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
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
                "Exit",
                fontSize = 30.sp,
                color = Color(0xFF007AFF),
                modifier = Modifier
            )
        }
    }
}

@Composable
fun RetryButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
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
                "Retry",
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
            .padding(bottom = 100.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = "Advarsel: Hvis du går ut av spillet uten å fullføre vil du ikke mota noen belønninger.",
            fontSize = 20.sp,
            lineHeight = 30.sp,
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
            .padding(top = 200.dp)
            .width(320.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .border(2.dp, Color.Blue, shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Du kan prøve denne testen igjen eller avslutte. \n" +
                    "Hva vil du gjøre?",
            fontSize = 24.sp,
            lineHeight = 36.sp,
            color = Color(0xFF6B6B6B),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun EmbarrassedImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(top = 35.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.embarrassed),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
        )
    }
    Box(
        modifier = modifier
            .padding(top = 80.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Oops!\n" +
                    "Du valgte feil svar.",
            fontSize = 32.sp,
            lineHeight = 48.sp,
            color = Color(0xFF000000),
            modifier = Modifier
        )
    }
}



/* TODO
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BachelorAppTheme {
        wrongAnswerObj.WrongAnswerShowScreen()
    }
}*/
