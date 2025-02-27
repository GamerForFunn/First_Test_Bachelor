package com.example.baseapptest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.layout.ContentScale
import android.widget.VideoView
import android.net.Uri
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.layout.*
import android.view.ViewGroup

// This is the activity that starts when the app is started.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //MainScreen for login
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                LoginScreen(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}
// Function for LoginScreen and state management for components
@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    // Without "mutableStateOf", the input text will not be remembered and displayed
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoggedIn by remember { mutableStateOf(false) }


// Adding animation background
    /*var videoPlayed by remember { mutableStateOf(false) }
    AndroidView(
        factory = { context ->
            VideoView(context).apply {
                val uri = Uri.parse("android.resource://${context.packageName}/raw/background")
                setVideoURI(uri)
                setOnPreparedListener {
                    it.isLooping = false
                    start()
                }
                setOnCompletionListener {
                    videoPlayed = true
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )

    LaunchedEffect(Unit) {
        videoPlayed = true
    }*/


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Viser enten en velkomstmelding eller innlogget status
        Text(
            text = if (isLoggedIn) "Innlogging vellykket! Du kan nå starte å spille."
            else "Velkommen!",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Check if the user is NOT logged in
        if (!isLoggedIn) {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Brukernavn") },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Passord") },
                shape = RoundedCornerShape(12.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(16.dp))

            //Login Button
            Button(
                onClick = { isLoggedIn = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF843A83))
            ) {
                Text(text = "Logg inn", color = Color.White)
            }
        }
    }
}
