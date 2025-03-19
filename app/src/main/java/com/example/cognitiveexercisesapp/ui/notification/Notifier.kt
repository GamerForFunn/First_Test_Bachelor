package com.example.cognitiveexercisesapp.ui.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.cognitiveexercisesapp.MainActivity
import com.example.cognitiveexercisesapp.R

class Notifier(private val context: Context) {

    // Change the target activity to MainActivity (or any other existing activity)
    private val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    // For snoozing the notification (optional action)
    private val snoozeIntent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    // Create a PendingIntent that will open MainActivity when the notification is tapped
    private val pendingIntent: PendingIntent =
        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    companion object {
        const val CHANNEL_ID = "cognitive_exercise_channel"
    }

    private val snoozePendingIntent: PendingIntent =
        PendingIntent.getBroadcast(context, 0, snoozeIntent, PendingIntent.FLAG_IMMUTABLE)

    fun builder(): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.sock)
            .setContentTitle("Cognitive Exercise App")
            .setContentText("Remember to exercise your brain!")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(
                        "Regularly exercising your brain is as important as staying physically active. " +
                                "Just like muscles, your brain benefits from a good workout—challenging puzzles, learning new skills, " +
                                "or even a thoughtful conversation can boost mental agility. " +
                                "Remember to set aside time each day for activities that stimulate your mind, " +
                                "as it keeps you sharp, creative, and ready to tackle everyday challenges."
                    )
            )
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.broccolitest, "Snooze", snoozePendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    }
}
