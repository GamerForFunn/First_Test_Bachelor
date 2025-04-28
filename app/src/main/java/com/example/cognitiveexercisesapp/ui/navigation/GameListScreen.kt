package com.example.cognitiveexercisesapp.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cognitiveexercisesapp.ui.data.languages.LanguageManager

@Composable
fun GameListScreen(navController: NavController) {
    val lang = LanguageManager.language.gameListScreen

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = lang.availableGames,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // List of available games with clickable cards
        GameCard(
            title = lang.game1Title,
            description = lang.game1Description,
            onClick = { navController.navigate(Routes.game1ScreenInstructions) }
        )

        GameCard(
            title = lang.game2Title,
            description = lang.game2Description,
            onClick = { navController.navigate(Routes.game2ScreenInstructions) }
        )

        GameCard(
            title = lang.game3Title,
            description = lang.game3Description,
            onClick = { navController.navigate(Routes.game3ScreenInstructions) }
        )
    }
}

@Composable
fun GameCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
