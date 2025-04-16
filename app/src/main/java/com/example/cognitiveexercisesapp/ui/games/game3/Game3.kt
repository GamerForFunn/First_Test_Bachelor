package com.example.cognitiveexercisesapp.ui.games.game3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cognitiveexercisesapp.ui.components.CountTime
import com.example.cognitiveexercisesapp.ui.components.WordPairingWordButton
import com.example.cognitiveexercisesapp.ui.components.countWrongAnswers
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Game3Screen (navController: NavController) {
    val viewModel: WordPairingViewModel = viewModel()
    val gameState by viewModel.gameState.observeAsState()

    // Counts the time spent in the game.
    CountTime()

    // Reset selection after validation
    LaunchedEffect(gameState?.isCorrectPair) {
        gameState?.isCorrectPair?.let {
            delay(3000) // Wait 3 second
            viewModel.resetSelection()
        }
    }

    if (gameState != null) {
        TopAppBar(
            title = { Text(text = "") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            ),
            actions = {
                Button(
                    onClick = { navController.navigate(Routes.game3ScreenInstructions) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "⏰ ${gameState!!.timeLeft}s",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Par sokkene!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 16.dp)
            )

            // Scattered sock layout
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                val words = listOf(
                    gameState!!.currentWordSet.word1,
                    gameState!!.currentWordSet.word2,
                    gameState!!.currentWordSet.word3
                )

                // Position each sock
                WordPairingWordButton(
                    word = words[0],
                    index = 0,
                    isSelected = gameState!!.selectedIndices.contains(0),
                    isCorrectPair = gameState!!.isCorrectPair,
                    onWordSelected = {
                                     word, index -> viewModel.onWordSelected(word, index, navController)
                                    countWrongAnswers += 1 // Counts wrong answers.
                                    }, // Pass navController
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 20.dp, y = 40.dp)
                )

                WordPairingWordButton(
                    word = words[1],
                    index = 1,
                    isSelected = gameState!!.selectedIndices.contains(1),
                    isCorrectPair = gameState!!.isCorrectPair,
                    onWordSelected = {
                                     word, index -> viewModel.onWordSelected(word, index, navController)
                                    countWrongAnswers += 1 // Counts wrong answers.
                                    }, // Pass navController
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = (-20).dp)
                )

                WordPairingWordButton(
                    word = words[2],
                    index = 2,
                    isSelected = gameState!!.selectedIndices.contains(2),
                    isCorrectPair = gameState!!.isCorrectPair,
                    onWordSelected = {
                                     word, index -> viewModel.onWordSelected(word, index, navController)
                                     countWrongAnswers += 1 // Counts wrong answers.
                                     }, // Pass navController
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(x = 20.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                contentAlignment = Alignment.Center
            ) {
                // Display status message
                gameState!!.statusMessage?.let { message ->
                    Text(
                        text = message,
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.hsl(250f, 0.33f, 0.32f)
                    )
                }
            }
        }
    }


}