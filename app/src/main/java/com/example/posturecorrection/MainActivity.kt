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
        //lateinit var NowState : MySharedPreferences
    }

    private lateinit var stateFragment:StateFragment
    private lateinit var chartFragment:ChartFragment


    //private val db = FirebaseFirestore.getInstance()






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(onBottomNavItemSelectedLister)
        stateFragment = StateFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.basic_fragment, stateFragment).commit()

        val Serviceintent = Intent(this , NotificationService::class.java)
        startService(Serviceintent)

/*
        // 실시간 데이터 가져오기
        val docRef = db.collection("user").document("position") // 콜렉션 , 문서 변경

        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(MainActivity.TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                //Log.d(MainActivity.TAG, "Current data: ${snapshot.data}")

                val Map = snapshot.data as Map<String, Any>

                when(Map.values.toString()){

                    "1" -> {
                        Log.d(MainActivity.TAG, "이프문작동")
                        now_image.setImageResource(R.drawable.video_icon)
                        message.text = "1번 자세 입니다."
                        NowState = "stable"

                    }
                    "2" ->
                    {
                        now_image.setImageResource(R.drawable.main_icon)
                        message.text = "2번 자세 입니다."
                        NowState = "stable"
                    }
                    "3" ->
                    {
                        now_image.setImageResource(R.drawable.graph_icon)
                        message.text = "3번 자세 입니다."
                        NowState = "shoulder"
                    }
                    "4" ->
                    {
                        now_image.setImageResource(R.drawable.app_logo)
                        message.text = "4번 자세 입니다."
                        NowState = "shoulder"

                    }
                    "5" ->
                    {
                        now_image.setImageResource(R.drawable.video_icon)
                        message.text = "5번 자세 입니다."
                        NowState = "waist"

                    }
                    "6" ->
                    {
                        now_image.setImageResource(R.drawable.main_icon)
                        message.text = "6번 자세 입니다."
                        NowState = "waist"

                    }
                    "7" ->
                    {
                        now_image.setImageResource(R.drawable.app_logo)
                        message.text = "7번 자세 입니다."
                        NowState = "back"

                    }
                    "8" ->
                    {
                        now_image.setImageResource(R.drawable.test_image)
                        message.text = "8번 자세 입니다."
                        NowState = "back"

                    }

                }

            } else {
                Log.d(MainActivity.TAG, "Current data: null")

            }
        }
        */

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