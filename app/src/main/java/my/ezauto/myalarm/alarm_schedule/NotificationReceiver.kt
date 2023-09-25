package my.ezauto.myalarm.alarm_schedule

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.google.android.material.button.MaterialButton
import my.ezauto.myalarm.NotificationCreate
import my.ezauto.myalarm.R

class NotificationReceiver:BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, p1: Intent?) {
        val notification = context?.let { NotificationCreate(it) }
        notification?.createNotification()

        val manager = context?.applicationContext?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.gravity = Gravity.CENTER
        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT

        layoutParams.alpha = 1.0f
        layoutParams.packageName = context.packageName
        layoutParams.buttonBrightness = 1f

        val view = View.inflate(context.applicationContext, R.layout.activity_popup,null)
        val exitButton = view.findViewById<MaterialButton>(R.id.warning_button)
        exitButton.setOnClickListener {
            manager.removeView(view)
        }

        manager.addView(view,layoutParams)
    }
}