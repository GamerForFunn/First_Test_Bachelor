package com.example.cognitiveexercisesapp.ui.notification

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object NotificationScheduler {

    // Checks if the notification is enabled in the settings.
    fun isNotificationEnabled(context: Context): Boolean {
        // Implement the logic to check if the notification is enabled in the settings.
        return true
    }

    // Function for scheduling periodic notifications with a specified interval (in minutes).
    // has to be minimum of 15 minutes according to android workmanager documentation.
    fun schedulePeriodicNotification(context: Context, intervalMinutes: Long = 15) {
        val notificationWorkRequest = PeriodicWorkRequestBuilder<NotificationWorker>(
            intervalMinutes, TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "NotificationWork",
            ExistingPeriodicWorkPolicy.KEEP,
            notificationWorkRequest
        )
    }

    // For testing: send a notification immediately (or after a delay if needed)
    private fun sendTestNotification(context: Context) {
        val notifier = Notifier(context)
        val notification = notifier.builder().build()
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
        notificationManager.notify(1, notification)
    }

    // For testing:
    // Schedules a test notification after a delay in milliseconds as specified by the parameter.
    fun scheduleTestNotification(context: Context, delayMillis: Long) {
        Handler(Looper.getMainLooper()).postDelayed({
            sendTestNotification(context)
        }, delayMillis)
    }
}
