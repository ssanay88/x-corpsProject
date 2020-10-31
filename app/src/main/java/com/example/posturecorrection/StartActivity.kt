package com.example.posturecorrection

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        Thread.sleep(1500)
        val intent = Intent(this, ChartActivity::class.java)
        startActivity(intent)



    }
}