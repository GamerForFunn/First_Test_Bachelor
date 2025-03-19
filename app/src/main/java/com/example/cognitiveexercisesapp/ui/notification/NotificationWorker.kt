package com.example.cognitiveexercisesapp.ui.notification

import android.app.NotificationManager
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        // Builds the notification using the Notifier class.
        val notifier = Notifier(applicationContext)
        val notification = notifier.builder().build()

        // Gets NotificationManager and issues the notification.
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }
}
