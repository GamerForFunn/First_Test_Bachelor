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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cognitiveexercisesapp.ui.data.GameInstructions
import com.example.cognitiveexercisesapp.ui.games.game1.Game1Config
import com.example.cognitiveexercisesapp.ui.games.game1.getAmountAndRangeBasedOnDifficulty
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import com.example.testforbachelor.ui.game_2.Game2ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Game2Screen(navController: NavController) {
    val viewModel : Game2ViewModel = viewModel()
    val randomImage1 by viewModel.randomImage1.collectAsState()
    val randomImage2 by viewModel.randomImage2.collectAsState()
    val randomImage3 by viewModel.randomImage3.collectAsState()
    val winnerImage by viewModel.winnerImage.collectAsState()
    val timerText by viewModel.timerText.collectAsState()
    val isRetryVisible by viewModel.isRetryVisible.collectAsState()
    val backgroundColor by viewModel.backgroundColor.collectAsState()

    // Track the current round
    var currentRound by remember { mutableStateOf(1) }

    // Handle round progression
    fun roundChecker() {
            if (currentRound < Game2Config.rounds) {
                // Move to the next round
                currentRound++
                viewModel.resetGame() //Starts function to restart game
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
            TopAppBar(
                title = { Text(text = "") },
                actions = {
                    Button(
                        onClick = { navController.navigate(Routes.game2ScreenInstructions) },
                        modifier = Modifier.padding(horizontal = 16.dp), // Add horizontal padding
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent) // Make the button background transparent
                    ) {
                        Text(
                            text = "Hjelp",
                            fontSize = 20.sp, // Increased font size
                            color = Color.Black // Set the text color
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = timerText,
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = when (GameInstructions.currentLanguage){ //Switches languages
                    "EN" -> "Please select the correct image!"
                    "NO" -> "Venligst velg det riktige bildet"
                    else -> "Venligst velg det riktige bildet"
                },
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(64.dp))
            Image(
                painter = painterResource(id = winnerImage),
                contentDescription = "Correct Image",
                modifier = Modifier.size(350.dp),
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
                        .size(120.dp)
                        .clickable { if(randomImage1 == winnerImage){roundChecker()} //Checking if imageID is the same as the winner image
                                   else{navController.navigate(Routes.wrongAnswer+"/Game2")}}, //If wrong will send to wrong screen and they have to start again
                    contentScale = ContentScale.Fit
                )
                Image(
                    painter = painterResource(id = randomImage2),
                    contentDescription = "Middle Image",
                    modifier = Modifier
                        .size(120.dp)
                        .clickable { if(randomImage2 == winnerImage){roundChecker()} //Checking if imageID is the same as the winner image
                        else{navController.navigate(Routes.wrongAnswer+"/Game2")}}, //If wrong will send to wrong screen and they have to start again
                    contentScale = ContentScale.Fit
                )
                Image(
                    painter = painterResource(id = randomImage3),
                    contentDescription = "Right Image",
                    modifier = Modifier
                        .size(120.dp)
                        .clickable { if(randomImage3 == winnerImage){roundChecker()} //Checking if imageID is the same as the winner image
                        else{navController.navigate(Routes.wrongAnswer+"/Game2")}},//If wrong will send to wrong screen and they have to start again
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(64.dp))
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