package com.cheng.currencyalert

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.drawable.IconCompat
//import com.cheng.apikit.R

// Show simple local notifications
object NotificationUtil {

    private const val TAG = "NotificationUtil"
    private const val NOTIFICATION_CHANNEL_ID = "ApiKit"
    private const val NOTIFICATION_CHANNEL_NAME = "Notification"

    fun showNotification(
        context: Context, notificationTitle: String, notificationText: String, smallIcon: IconCompat? = null
    ) {
        createNotificationChannelIfNotExist(context, NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME)
        if (!isNotificationEnabled(context, NOTIFICATION_CHANNEL_ID)) {
            Log.e(TAG, "Notification is not enabled, please double check the permission.")

            return
        }
        val notificationId = (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        notificationBuilder
            .setAutoCancel(true)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setStyle(NotificationCompat.BigTextStyle().bigText(notificationText))
//        if (smallIcon == null) {
//            notificationBuilder.setSmallIcon(R.drawable.ic_info_24dp)
//        } else {
//            notificationBuilder.setSmallIcon(smallIcon)
//        }

        manager.notify(notificationId, notificationBuilder.build())
    }

    fun createNotificationChannelIfNotExist(context: Context, channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val existingChannel = manager.getNotificationChannel(channelId)
            if (existingChannel == null) {
                val channel = NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_DEFAULT
                )
                channel.enableVibration(false)
                manager.createNotificationChannel(channel)
            }
        }
    }

    fun isNotificationEnabled(context: Context, channelId: String): Boolean {
        val notificationEnabled = NotificationManagerCompat.from(context).areNotificationsEnabled()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = manager.getNotificationChannel(channelId) ?: return false

            return channel.importance != NotificationManager.IMPORTANCE_NONE && notificationEnabled
        }

        return notificationEnabled
    }

}
