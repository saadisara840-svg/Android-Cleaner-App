package com.example.applicationdenettoyage.utils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

object NotificationUtils {

    private const val CHANNEL_ID = "clean_channel"
    private const val PROGRESS_ID = 1
    private const val FINISH_ID = 2
    private const val AUTO_STATUS_ID = 3


    private fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Nettoyage",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun canNotify(context: Context): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
    }

    fun showProgress(context: Context, progress: Int) {
        if (!canNotify(context)) return
        createChannel(context)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_menu_delete)
            .setContentTitle("Nettoyage en cours")
            .setContentText("Progress : $progress %")
            .setProgress(100, progress, false)
            .setOngoing(true)
            .build()

        try {
            NotificationManagerCompat.from(context)
                .notify(PROGRESS_ID, notification)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    fun showCleaningFinished(context: Context, freed: Long) {
        if (!canNotify(context)) return
        createChannel(context)

        try {

            NotificationManagerCompat.from(context).cancel(PROGRESS_ID)

            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_menu_delete)
                .setContentTitle("Nettoyage terminé")
                .setContentText("Espace libéré : ${freed / 1024} Ko")
                .setAutoCancel(true)
                .build()

            NotificationManagerCompat.from(context)
                .notify(FINISH_ID, notification)

        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
    fun showAutoCleanStatus(context: Context, enabled: Boolean) {
        if (!canNotify(context)) return
        createChannel(context)

        val statusText =
            if (enabled) "Auto Clean : ON" else "Auto Clean : OFF"

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_popup_sync)
            .setContentTitle("Application de Nettoyage")
            .setContentText(statusText)
            .setOngoing(true)
            .build()

        try {
            NotificationManagerCompat.from(context)
                .notify(AUTO_STATUS_ID, notification)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

}
