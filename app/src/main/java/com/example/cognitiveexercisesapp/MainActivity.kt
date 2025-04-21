package com.example.cognitiveexercisesapp

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

import android.app.usage.UsageStatsManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.cognitiveexercisesapp.services.UsageMonitoringService

import com.example.cognitiveexercisesapp.ui.components.ComparisonChartScreen
import com.example.cognitiveexercisesapp.ui.components.DoctorsCommentShowScreen
import com.example.cognitiveexercisesapp.ui.components.ExerciseFinished
import com.example.cognitiveexercisesapp.ui.components.WrongAnswerScreen
import com.example.cognitiveexercisesapp.ui.games.game1.Game1Screen
import com.example.cognitiveexercisesapp.ui.games.game2.Game2Screen
import com.example.cognitiveexercisesapp.ui.games.game3.Game3Screen
import com.example.cognitiveexercisesapp.ui.navigation.GameListScreen
import com.example.cognitiveexercisesapp.ui.navigation.HomeScreen
import com.example.cognitiveexercisesapp.ui.navigation.Instructions.Game1ScreenInstructions
import com.example.cognitiveexercisesapp.ui.navigation.Instructions.Game2ScreenInstructions
import com.example.cognitiveexercisesapp.ui.navigation.Instructions.Game3ScreenInstructions
import com.example.cognitiveexercisesapp.ui.navigation.Routes
import com.example.cognitiveexercisesapp.ui.notification.NotificationScheduler
import com.example.cognitiveexercisesapp.ui.notification.Notifier
import com.example.cognitiveexercisesapp.ui.theme.CognitiveExercisesAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var overlayPermissionLauncher: ActivityResultLauncher<Intent>

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val notifierObj = Notifier(this)

        overlayPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            // Optionally, you can check if permission was granted and take further action.
        }

        // Check and request overlay permission if not granted.
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )
            overlayPermissionLauncher.launch(intent)
        }

        // Prompts the user for both normal notifications and pop-up notification.
        // Checks for normal notifications first.
        if (notifierObj.promptNotificationPermission(this)) {
            notifierObj.promptSystemNotificationPermission(this)
        }

        /* This is responsible for the periodic notification that will be sent when the app is not
        * open. According to the Android WorkManager documentation, the minimum cycle length
        * is 15 minutes. */
        NotificationScheduler.schedulePeriodicNotification(this)

        /* This notification is only for testing the actual notification design wise, txt etc.
        * Will be remover or commented out later. */
        NotificationScheduler.scheduleTestNotification(this, 3_000)

        // Request usage access permission and start monitoring service if permission is granted
        requestUsageAccessPermission()

        enableEdgeToEdge()
        setContent {
            CognitiveExercisesAppTheme {
                MainScreenWithBottomNav()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (hasUsageAccessPermission()) {
            startUsageMonitoringService()
        }
    }

    private fun requestUsageAccessPermission() {
        if (!hasUsageAccessPermission()) {
            Toast.makeText(
                this,
                "Vi trenger brukstilgang for å gi deg tilpassede treningspåminnelser",
                Toast.LENGTH_LONG
            ).show()

            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            startActivity(intent)
        } else {
            // Start the service if we already have permission
            startUsageMonitoringService()
        }
    }

    private fun hasUsageAccessPermission(): Boolean {
        val usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val currentTime = System.currentTimeMillis()
        // Check if we can get any stats at all
        val stats = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            currentTime - 1000*60*60*24,
            currentTime
        )
        return stats.isNotEmpty()
    }

    private fun startUsageMonitoringService() {
        if (hasUsageAccessPermission()) {
            UsageMonitoringService.start(this)
            Log.d("MainActivity", "Usage monitoring service started")
        } else {
            Log.d("MainActivity", "Cannot start service - no usage access permission")
        }
    }
}

@Composable
fun MainScreenWithBottomNav() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = Routes.homeTab // Create a new route for the home tab
            ) {
                // Tab level navigation
                composable(Routes.homeTab) {
                    HomeTab(navController)
                }
                composable(Routes.gamesTab) {
                    GamesTab(navController)
                }

                // Existing screens (these will be navigated to from either tab)
                composable(Routes.wrongAnswer + "/{currentGame}") {
                    val currentGame = it.arguments?.getString("currentGame")
                    WrongAnswerScreen(navController, currentGame ?: "No Game")
                }
                composable(Routes.comparisonChart) {
                    ComparisonChartScreen(navController)
                }
                composable(Routes.exerciseFinished) {
                    ExerciseFinished(navController)
                }
                composable(Routes.doctorsComment) {
                    DoctorsCommentShowScreen(navController)
                }
                composable(Routes.game1ScreenInstructions) {
                    Game1ScreenInstructions(navController)
                }
                composable(Routes.game1Screen) {
                    Game1Screen(navController)
                }
                composable(Routes.game2ScreenInstructions) {
                    Game2ScreenInstructions(navController)
                }
                composable(Routes.game2Screen) {
                    Game2Screen(navController)
                }
                composable(Routes.game3ScreenInstructions) {
                    Game3ScreenInstructions(navController)
                }
                composable(Routes.game3Screen) {
                    Game3Screen(navController)
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Only show the bottom nav when we're on one of the main tabs
    val showBottomNav = currentRoute == Routes.homeTab || currentRoute == Routes.gamesTab

    if (showBottomNav) {
        NavigationBar {
            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, contentDescription = "Hjem") },
                label = { Text("Hjem") },
                selected = currentRoute == Routes.homeTab,
                onClick = {
                    navController.navigate(Routes.homeTab) {
                        popUpTo(Routes.homeTab) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )

            NavigationBarItem(
                icon = { Icon(Icons.Default.PlayArrow, contentDescription = "Spill") },
                label = { Text("Spill") },
                selected = currentRoute == Routes.gamesTab,
                onClick = {
                    navController.navigate(Routes.gamesTab) {
                        popUpTo(Routes.homeTab) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun HomeTab(navController: NavController) {
    HomeScreen(navController)
}

@Composable
fun GamesTab(navController: NavController) {
    GameListScreen(navController)
}