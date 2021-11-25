package com.example.posturecorrection

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

// 스플래시 스크린용 (splash screen)

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        Log.d(MainActivity.TAG, "PASS}")
        Thread.sleep(1500)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}