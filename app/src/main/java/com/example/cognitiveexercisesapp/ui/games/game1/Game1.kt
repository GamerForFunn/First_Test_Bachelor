package com.example.cognitiveexercisesapp.ui.games.game1

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import kotlin.random.Random

@Composable
fun Game1Screen(navController: NavController, difficulty: Int = 10) {
    val numCount = (5 + (difficulty * 0.15)).toInt().coerceAtMost(20)
    val numberList = remember { generateNumbers(numCount) }
    val sortedNumbers = remember { numberList.sorted() }
    var currentIndex by remember { mutableStateOf(0) }
    var round by remember { mutableStateOf(1) }
    val clickedNumbers = remember { mutableStateListOf<Int>() }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Round $round/3", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Box(modifier = Modifier.fillMaxSize()) {
            numberList.forEach { num ->
                val positionX = Random.nextInt(50, 300).dp
                val positionY = Random.nextInt(100, 600).dp
                DiamondButton(number = num, positionX, positionY, clickedNumbers.contains(num)) {
                    if (num == sortedNumbers[currentIndex]) {
                        clickedNumbers.add(num)
                        currentIndex++
                        if (currentIndex == sortedNumbers.size) {
                            if (round < 3) {
                                round++
                                currentIndex = 0
                                clickedNumbers.clear()
                            } else {
                                navController.navigate(Routes.exerciseFinished)
                            }
                        }
                    } else {
                        navController.navigate(Routes.wrongAnswer)
                    }
                }
            }
        }
    }
}

@Composable
fun DiamondButton(number: Int, x: Dp, y: Dp, isClicked: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .offset(x, y)
            .size(60.dp)
            .background(if (isClicked) Color.Green else Color.Blue)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = number.toString(), fontSize = 20.sp, color = Color.White)
    }
}

fun generateNumbers(count: Int): List<Int> {
    return (1..99).shuffled().take(count)
}
