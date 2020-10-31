package com.example.posturecorrection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignInClient

class LoginActivity :  AppCompatActivity() {

    var googleSignInClient : GoogleSignInClient? = null
    val RC_SIGN_IN = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)




    }




}