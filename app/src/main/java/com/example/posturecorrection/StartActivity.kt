package com.example.posturecorrection

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

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