package my.ezauto.myalarm.alarm_schedule

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import my.ezauto.myalarm.MyApplication
import my.ezauto.myalarm.R
import my.ezauto.myalarm.databinding.FragmentAlarmBinding
import java.util.*


class AlarmFragment : Fragment() {
    lateinit var binding: FragmentAlarmBinding
    private val permReqLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){}

    @RequiresApi(33)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_alarm, container, false)
        permReqLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)

        binding.saveTime.setOnClickListener {
            val hour = binding.timePicker.hour
            val minute = binding.timePicker.minute

            startWork(hour, minute)
        }

        return  binding.root
    }

    private fun startWork(hour:Int , minute:Int) {
        val calender = Calendar.getInstance()
        calender.set(Calendar.HOUR_OF_DAY,hour)
        calender.set(Calendar.MINUTE,minute)
        calender.set(Calendar.SECOND,0)
        if (Calendar.getInstance().after(calender)){
            calender.add(Calendar.DATE, 1)
        }

        val alarmManager = requireActivity().getSystemService(ALARM_SERVICE) as AlarmManager

        val intent = Intent(MyApplication.appContext, NotificationReceiver::class.java)
        // Cancel alarms
        try {
            val pendingUpdateIntent = PendingIntent.getService(context, 0, intent,PendingIntent.FLAG_MUTABLE)
            alarmManager.cancel(pendingUpdateIntent)
        } catch (e: Exception) {
            Log.e("TAG", "AlarmManager update was not canceled. $e")
        }

        val pendingIntent = PendingIntent.getBroadcast(MyApplication.appContext,0,intent,PendingIntent.FLAG_MUTABLE )

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calender.timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent)

       // Toast.makeText(requireContext(), "Alarm set successfully!",Toast.LENGTH_LONG).show()
        val snackbar = Snackbar.make(binding.rootView,"Alarm set successfully!",Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.snackbar_color))
        snackbar.setTextColor(ContextCompat.getColor(requireContext(), R.color.snackbar_text_color))
        snackbar.show()

    }
    // https://stackoverflow.com/questions/21179825/how-to-open-alert-dialog-system-level-in-android/21182403#21182403

}