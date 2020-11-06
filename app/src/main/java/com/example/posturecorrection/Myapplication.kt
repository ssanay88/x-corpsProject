package com.example.posturecorrection

import android.app.Application

class Myapplication: Application() {
    companion object {
        lateinit var prefs: MySharedPreferences
    }
    override fun onCreate() {
        prefs = MySharedPreferences(applicationContext)
        super.onCreate()
    }


}