package my.ezauto.myalarm
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import my.ezauto.myalarm.alarm_schedule.AlarmFragment
import my.ezauto.myalarm.databinding.ActivityMainBinding
import my.ezauto.myalarm.swap_image.SwapPicFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(AlarmFragment())

        binding.bottomNavigation.setOnItemSelectedListener {menuItem ->
            when(menuItem.itemId){
                R.id.alarm_page ->{
                   loadFragment(AlarmFragment())
                }
                R.id.image_swap_page ->{
                    loadFragment(SwapPicFragment())
                }
            }
            true
        }

    }
    private  fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
    }
}