package com.example.cognitiveexercisesapp.ui.notification

import android.app.NotificationManager
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.cognitiveexercisesapp.ui.notification.Notifier

class NotificationWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        // Build the notification using your Notifier helper class.
        val notifier = Notifier(applicationContext)
        val notification = notifier.builder().build()

        // Get NotificationManager and issue the notification.
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }
}
