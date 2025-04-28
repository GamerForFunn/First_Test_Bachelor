package com.example.cognitiveexercisesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cognitiveexercisesapp.ui.data.languages.LanguageManager
import com.example.cognitiveexercisesapp.ui.navigation.Routes

@Composable
fun TopBar(helpRoute: String, navController: NavController, timerText: String){
    val lang = LanguageManager.language.commonGameTexts
    Box(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFEDF4F4))
            .height(72.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Exit button
            IconButton(
                onClick = { navController.navigate(Routes.homeScreen) },
                modifier = Modifier.size(48.dp) // Fixed size for button
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Avslutt",
                    tint = Color.Black
                )
            }

            // Timer in center
            Text(
                text = timerText,
                style = TextStyle(fontSize = 24.sp),
            )

            // Help button
            Button(
                onClick = { navController.navigate(helpRoute) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier.height(48.dp)
            ) {
                Text(
                    text = lang.help,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        }
    }
}