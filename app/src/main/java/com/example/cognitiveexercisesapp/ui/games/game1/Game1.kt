package com.example.cognitiveexercisesapp.ui.games.game1

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cognitiveexercisesapp.ui.components.CountTime
import com.example.cognitiveexercisesapp.ui.components.TopBar
import com.example.cognitiveexercisesapp.ui.components.countWrongAnswers
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Game1Screen(navController: NavController) {
    // Track the current round
    var currentRound by remember { mutableStateOf(1) }

    // Store the grid list in state so it resets each round
    var gridList by remember { mutableStateOf(getAmountAndRangeBasedOnDifficulty()) }

    // Track the lowest available number that should be clicked next
    var nextNumberToClick by remember { mutableStateOf(gridList.filterNotNull().minOrNull() ?: 1) }

    // Check if all numbers are null (round finished)
    val isRoundFinished = gridList.all { it == null }

    // TODO fix timer logic for all games
    val _timerTextGame1 = MutableStateFlow("00:00")
    val timerTextGame1: StateFlow<String> = _timerTextGame1.asStateFlow()
    val timerText by timerTextGame1.collectAsState()

    // Handle round progression
    LaunchedEffect(isRoundFinished) {
        if (isRoundFinished) {
            if (currentRound < Game1Config.rounds) {
                // Move to the next round and reset the grid
                currentRound++
                gridList = getAmountAndRangeBasedOnDifficulty()
                nextNumberToClick = gridList.filterNotNull().minOrNull() ?: 1
            } else {
                // Navigate to exerciseFinished after all rounds
                navController.navigate(Routes.exerciseFinished)
            }
        }
    }

    // Counts the time spent in the game.
    CountTime()

    // Wrap everything in a Box to center content
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Center children horizontally
        ) {
            TopBar(Routes.game1ScreenInstructions, navController, timerText)
            // Display the current round
            if (Game1Config.rounds > 1){
                Text(
                    text = "Round $currentRound/${Game1Config.rounds}",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            // Display next number to click if enabled in Game1Config
            if (Game1Config.showNextClick) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Next to click: $nextNumberToClick",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Space between text and grid

            // Centered Grid
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                for (row in 0 until Game1Config.rows) {
                    Row {
                        for (column in 0 until Game1Config.columns) {
                            val index = row * Game1Config.columns + column
                            val number = gridList.getOrNull(index)

                            if (number != null) {
                                // Normal button
                                Button(
                                    onClick = {
                                        if (number != nextNumberToClick) {
                                            navController.navigate(Routes.wrongAnswer+"/Game1")
                                            countWrongAnswers += 1 // Increases wrong answer count.
                                        } else {
                                            gridList = gridList.toMutableList().also { it[index] = null }
                                            nextNumberToClick = gridList.filterNotNull().minOrNull() ?: -1
                                        }
                                    },
                                    shape = androidx.compose.foundation.shape.CircleShape,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .size(70.dp)
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = number.toString(),
                                            fontSize = 22.sp,
                                            textAlign = TextAlign.Center,
                                            maxLines = 1,
                                            softWrap = false,
                                            overflow = androidx.compose.ui.text.style.TextOverflow.Visible,
                                            modifier = Modifier.width(IntrinsicSize.Min),
                                            letterSpacing = (-1.sp)
                                        )

                                    }
                                }

                            } else {
                                // Invisible placeholder
                                Box(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .size(width = 70.dp, height = 70.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}