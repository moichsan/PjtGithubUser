package com.moichsan.githubusers.ui.setting

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.moichsan.githubusers.R
import java.util.*

object AlarmNotification {
    fun showNotification(
        context: Context,
        title: String,
        message: String,
        notificationId: Int,
        pendingIntent: PendingIntent
    ) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, "channel_id")
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_favorite)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_favorite))
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channel_id",
                "channel_name",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            builder.setChannelId("channel_id")
            notificationManager.createNotificationChannel(channel)
        }

        val notification = builder.build()
        notificationManager.notify(notificationId, notification)
    }

    fun enabledAlarm(
        context: Context,
        title: String,
        message: String,
        requestCode: Int,
        time: Calendar
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra("title", title)
        intent.putExtra("message", message)
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            time.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    fun disabledAlarm(
        context: Context,
        requestCode: Int
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
    }
}