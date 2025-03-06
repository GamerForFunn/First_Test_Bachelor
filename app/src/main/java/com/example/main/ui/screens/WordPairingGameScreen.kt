package com.example.main.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.main.ui.components.WordPairingWordButton
import com.example.main.viewmodels.WordPairingViewModel
import kotlinx.coroutines.delay

@Composable
fun WordPairingGameScreen() {
    val viewModel: WordPairingViewModel = viewModel()
    val gameState by viewModel.gameState.observeAsState()

    // Reset selection after validation
    LaunchedEffect(gameState?.isCorrectPair) {
        gameState?.isCorrectPair?.let {
            delay(1000) // Wait 1 second
            viewModel.resetSelection()
        }
    }

    if (gameState != null) {
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
                text = "Lignende betydning",
                style = MaterialTheme.typography.headlineSmall,
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
                    onWordSelected = viewModel::onWordSelected,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 20.dp, y = 40.dp)
                )

                WordPairingWordButton(
                    word = words[1],
                    index = 1,
                    isSelected = gameState!!.selectedIndices.contains(1),
                    isCorrectPair = gameState!!.isCorrectPair,
                    onWordSelected = viewModel::onWordSelected,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = (-20).dp)
                )

                WordPairingWordButton(
                    word = words[2],
                    index = 2,
                    isSelected = gameState!!.selectedIndices.contains(2),
                    isCorrectPair = gameState!!.isCorrectPair,
                    onWordSelected = viewModel::onWordSelected,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(x = 20.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp), // Fixed height for message area
                contentAlignment = Alignment.Center
            ) {
                // Display status message (if any)
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