package my.ezauto.myalarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi


class NotificationCreate(var context: Context) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotification(){
        val notificationBuilder = Notification.Builder(context,CHANNEL_ID).let {
            it.setContentTitle("Hey! It's time of your Sleep")
            it.setContentText("It is already time of your sleep. Take a sleep like good boy")
            it.setAutoCancel(false)
            it.setSmallIcon(R.drawable.ic_bedtime)
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationChannel = NotificationChannel(CHANNEL_ID,NOTIFICATION_NAME,NotificationManager.IMPORTANCE_HIGH).apply {
            enableLights(true)
            lightColor = Color.RED
            enableVibration(true)
            setSound(Uri.parse("android.resource://"+context.packageName+ "/" +R.raw.notification_sound),
            AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE).build())
        }
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(9067, notificationBuilder.build())


    }

    companion object{
        const val CHANNEL_ID = "my.alarm.manager"
        const val NOTIFICATION_NAME = "my.alarm.manager.notification.channel"
    }
}