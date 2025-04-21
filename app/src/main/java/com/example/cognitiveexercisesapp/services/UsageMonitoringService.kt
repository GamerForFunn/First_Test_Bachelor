package com.example.cognitiveexercisesapp.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.app.usage.UsageEvents
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.cognitiveexercisesapp.R
import com.example.cognitiveexercisesapp.ui.components.ComposeOverlayService
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class UsageMonitoringService : Service() {
    private val serviceScope = CoroutineScope(Dispatchers.Default + Job())
    private lateinit var usageStatsManager: UsageStatsManager
    private var monitoringJob: Job? = null

    // Chrome tracking variables
    private var chromeStartTime: Long = 0
    private var isTrackingChrome = false
    private var lastPromptTime: Long = 0

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service onCreate called")
        usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        startMonitoring()
    }

    private fun startMonitoring() {
        Log.d(TAG, "Starting monitoring job")

        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification())

        monitoringJob = serviceScope.launch {
            while (isActive) {
                checkCurrentAppUsage()
                delay(CHECK_INTERVAL)
            }
        }
    }

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            MONITORING_CHANNEL_ID,
            "Kognitiv Trening Overvåkning",
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = "Overvåker appbruk for å gi kognitiv trening"
            setShowBadge(false)
        }
        notificationManager.createNotificationChannel(channel)
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, MONITORING_CHANNEL_ID)
            .setContentTitle("Kognitiv Trening")
            .setContentText("Overvåker appbruk for treningspåminnelser")
            .setSmallIcon(R.drawable.sock) // Using your existing icon
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)
            .build()
    }

    private fun checkCurrentAppUsage() {
        try {
            val endTime = System.currentTimeMillis()
            val startTime = endTime - TimeUnit.MINUTES.toMillis(1)

            val usageEvents = usageStatsManager.queryEvents(startTime, endTime)
            val event = UsageEvents.Event()
            var mostRecentForegroundApp: String? = null
            var mostRecentTimestamp: Long = 0

            while (usageEvents.hasNextEvent()) {
                usageEvents.getNextEvent(event)
                if (event.eventType == UsageEvents.Event.MOVE_TO_FOREGROUND &&
                    event.timeStamp > mostRecentTimestamp) {
                    mostRecentForegroundApp = event.packageName
                    mostRecentTimestamp = event.timeStamp
                }
            }

            mostRecentForegroundApp?.let { packageName ->
                Log.d(TAG, "Current foreground app: $packageName")
                handleChromeUsage(packageName)
            }

        } catch (e: Exception) {
            Log.e(TAG, "Error checking app usage", e)
        }
    }

    private fun handleChromeUsage(packageName: String) {
        val isChromeActive = packageName == CHROME_PACKAGE_NAME

        when {
            isChromeActive && !isTrackingChrome -> {
                // Chrome just became active
                chromeStartTime = System.currentTimeMillis()
                isTrackingChrome = true
                Log.d(TAG, "Started tracking Chrome usage")
            }
            !isChromeActive && isTrackingChrome -> {
                // Chrome is no longer active
                isTrackingChrome = false
                chromeStartTime = 0
                Log.d(TAG, "Stopped tracking Chrome usage")
            }
            isChromeActive && isTrackingChrome -> {
                // Chrome is still active, check duration
                val currentDuration = System.currentTimeMillis() - chromeStartTime
                val durationInSeconds = currentDuration / 1000
                Log.d(TAG, "Chrome usage duration: $durationInSeconds seconds")

                if (durationInSeconds >= CHROME_TRIGGER_DURATION_SECONDS && !hasShownPromptRecently()) {
                    showTrainingPrompt()
                }
            }
        }
    }

    private fun hasShownPromptRecently(): Boolean {
        val currentTime = System.currentTimeMillis()
        val timeSinceLastPrompt = currentTime - lastPromptTime

        // Don't show prompts more often than every 30 minutes
        return timeSinceLastPrompt < TimeUnit.MINUTES.toMillis(PROMPT_COOLDOWN_MINUTES)
    }

    private fun showTrainingPrompt() {
        Log.d(TAG, "Showing training prompt after ${CHROME_TRIGGER_DURATION_SECONDS} seconds of Chrome usage")

        // Update the last prompt time
        lastPromptTime = System.currentTimeMillis()

        // Show the overlay using the existing ComposeOverlayService
        ComposeOverlayService.showOverlay(this)

        // Reset tracking after showing prompt
        isTrackingChrome = false
        chromeStartTime = 0
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service onStartCommand called")
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        Log.d(TAG, "Service onDestroy called")
        super.onDestroy()
        monitoringJob?.cancel()
        serviceScope.cancel()
    }

    companion object {
        private const val TAG = "UsageMonitoringService"
        private const val CHECK_INTERVAL = 5000L // 5 seconds
        private const val MONITORING_CHANNEL_ID = "CognitiveTrainingMonitoring"
        private const val NOTIFICATION_ID = 9001
        private const val CHROME_PACKAGE_NAME = "com.android.chrome"
        private const val CHROME_TRIGGER_DURATION_SECONDS = 10L // Show prompt after 10 seconds
        private const val PROMPT_COOLDOWN_MINUTES = 10L // Don't show prompts more often than every 10 seconds

        fun start(context: Context) {
            val intent = Intent(context, UsageMonitoringService::class.java)
            context.startService(intent)
        }

        fun stop(context: Context) {
            val intent = Intent(context, UsageMonitoringService::class.java)
            context.stopService(intent)
        }
    }
}