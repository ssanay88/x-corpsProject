package com.example.posturecorrection

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.util.SharedPreferencesUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_state.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*



class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG: String = "로그"
    }

    private lateinit var stateFragment:StateFragment    // 현재 상태를 보여주는 프래그먼트
    private lateinit var chartFragment:ChartFragment    // 차트를 보여주기 위한 프래그먼트


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(onBottomNavItemSelectedLister)
        stateFragment = StateFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.basic_fragment, stateFragment).commit()

        val Serviceintent = Intent(this , NotificationService::class.java)
        startService(Serviceintent)


    }



    //바텀네비게이션 아이템 클릭 리스너 설정
    private val onBottomNavItemSelectedLister = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.page_1 -> {
                //Toast.makeText(this, "${YEAR} + ${MONTH}", Toast.LENGTH_SHORT).show()
                stateFragment = StateFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(
                    R.id.basic_fragment,
                    stateFragment
                ).commit()
                true

            }
            R.id.page_2 -> {
                //Toast.makeText(this, "${min} + ${sec} + ${DAY}", Toast.LENGTH_SHORT).show()
                chartFragment = ChartFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(
                    R.id.basic_fragment,
                    chartFragment
                ).commit()
                true
            }
            else -> false
        }
    }










}