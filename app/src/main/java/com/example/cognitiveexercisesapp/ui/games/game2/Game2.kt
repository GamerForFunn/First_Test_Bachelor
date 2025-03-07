package com.example.cognitiveexercisesapp.ui.games.game2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cognitiveexercisesapp.ui.games.game1.Game1Config
import com.example.cognitiveexercisesapp.ui.games.game1.getAmountAndRangeBasedOnDifficulty
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import com.example.testforbachelor.ui.game_2.Game2ViewModel

@Composable
fun Game2Screen(navController: NavController) {
    val viewModel : Game2ViewModel = viewModel()
    val randomImage1 by viewModel.randomImage1.collectAsState()
    val randomImage2 by viewModel.randomImage2.collectAsState()
    val randomImage3 by viewModel.randomImage3.collectAsState()
    val winnerImage by viewModel.winnerImage.collectAsState()
    val game2WelcomeText by viewModel.game2WelcomeText.collectAsState()
    val timerText by viewModel.timerText.collectAsState()
    val isRetryVisible by viewModel.isRetryVisible.collectAsState()
    val backgroundColor by viewModel.backgroundColor.collectAsState()

    // Track the current round
    var currentRound by remember { mutableStateOf(1) }

    // Store the grid list in state so it resets each round
    var gridList by remember { mutableStateOf(getAmountAndRangeBasedOnDifficulty()) }


    // Check if all numbers are null (round finished)
    val isRoundFinished = gridList.all { it == null }

    // Handle round progression
    fun roundChecker() {

            if (currentRound < Game2Config.rounds) {
                // Move to the next round and reset the grid
                currentRound++
                viewModel.resetGame()

            } else {
                // Navigate to exerciseFinished after all rounds
                navController.navigate(Routes.exerciseFinished)
            }

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = timerText,
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = game2WelcomeText,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = winnerImage),
                contentDescription = "Correct Image",
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = randomImage1),
                    contentDescription = "Left Image",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { if(randomImage1 == winnerImage){navController.navigate(Routes.exerciseFinished)}
                                   else{navController.navigate(Routes.wrongAnswer+"/Game2")}},
                    contentScale = ContentScale.Fit
                )
                Image(
                    painter = painterResource(id = randomImage2),
                    contentDescription = "Middle Image",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { if(randomImage2 == winnerImage){navController.navigate(Routes.exerciseFinished)}
                        else{navController.navigate(Routes.wrongAnswer+"/Game2")}},
                    contentScale = ContentScale.Fit
                )
                Image(
                    painter = painterResource(id = randomImage3),
                    contentDescription = "Right Image",
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { if(randomImage3 == winnerImage){navController.navigate(Routes.exerciseFinished)}
                        else{navController.navigate(Routes.wrongAnswer+"/Game2")}},
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
        if (isRetryVisible) {
            Button(
                onClick = {viewModel.onRetryClick()},
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "Retry", color = Color.White)
            }
        }
    }
}


