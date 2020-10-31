package com.example.posturecorrection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Bottomnavigation 선택에 따른 행동 추가
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.page_1 -> {

                    true
                }

                R.id.page_2 -> {

                    true
                }

                R.id.page_3 -> {

                    true
                }

                else -> false
            }
        }


    }
}