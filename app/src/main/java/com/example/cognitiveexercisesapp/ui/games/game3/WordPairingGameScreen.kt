package com.example.cognitiveexercisesapp.ui.games.game3

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
import com.example.cognitiveexercisesapp.ui.components.WordPairingWordButton
import com.example.cognitiveexercisesapp.ui.games.game3.WordPairingViewModel
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "⏰ ${gameState!!.timeLeft}s",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Level: ${gameState!!.currentLevel}",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Score: ${gameState!!.score}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Create a list of words with their indices
            val words = listOf(
                gameState!!.currentWordSet.word1,
                gameState!!.currentWordSet.word2,
                gameState!!.currentWordSet.word3
            )

            // Render buttons with indices
            words.forEachIndexed { index, word ->
                WordPairingWordButton(
                    word = word,
                    index = index,
                    isSelected = gameState!!.selectedIndices.contains(index),
                    isCorrectPair = gameState!!.isCorrectPair,
                    onWordSelected = { selectedWord, selectedIndex ->
                        viewModel.onWordSelected(selectedWord, selectedIndex)
                    }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Display status message (if any)
            gameState!!.statusMessage?.let { message ->
                Text(
                    text = message,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.hsl(250f, 0.33f, 0.32f),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}