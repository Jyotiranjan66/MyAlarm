package my.ezauto.myalarm.alarm_schedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import my.ezauto.myalarm.R

class PopupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_popup)

//        lifecycleScope.launch(Dispatchers.Main){
//         delay(3000)
//         finish()
//        }
    }
}