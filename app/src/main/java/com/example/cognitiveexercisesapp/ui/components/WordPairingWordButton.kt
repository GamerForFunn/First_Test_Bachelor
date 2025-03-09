package com.example.cognitiveexercisesapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cognitiveexercisesapp.R

@Composable
fun WordPairingWordButton(
    word: String,
    index: Int,
    isSelected: Boolean,
    isCorrectPair: Boolean?,
    onWordSelected: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // Determine button color based on selection state
    val backgroundColor = when {
        isSelected && isCorrectPair == true -> Color.Green.copy(alpha = 0.3f)
        isSelected && isCorrectPair == false -> Color.Red.copy(alpha = 0.3f)
        isSelected -> Color.Gray.copy(alpha = 0.3f)
        else -> Color.Transparent
    }

    // Calculate rotation of sock image and mirror it based on index
    val (rotation, shouldMirror) = when (index) {
        0 -> Pair(35f, true)  // Top sock
        1 -> Pair(-35f, false)    // Middle sock
        else -> Pair(35f, true) // Bottom sock
    }

    Box(
        modifier = modifier
            .width(140.dp)
            .clickable { onWordSelected(word, index) },
        contentAlignment = Alignment.Center
    ) {
        // Sock image as background
        Image(
            painter = painterResource(id = R.drawable.sock),
            contentDescription = "Word sock for $word",
            modifier = Modifier
                .width(140.dp)
                .height(180.dp)
                .graphicsLayer {
                    rotationZ = rotation
                    scaleX = if (shouldMirror) -1f else 1f
                },
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(
                color = backgroundColor,
                blendMode = BlendMode.SrcAtop
            )
        )

        // Word centered in the sock
        Text(
            text = word,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 40.dp),
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black
        )
    }
}
