package com.example.posturecorrection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.page_1 -> {

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.page_2 -> {

                    val intent = Intent(this, ChartActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.page_3 -> {

                    val intent = Intent(this, VideoActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }
    }
}