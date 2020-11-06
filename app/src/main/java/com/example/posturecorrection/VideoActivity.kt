package com.example.posturecorrection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_video.*


class VideoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        Log.d(MainActivity.TAG, "비디오 액티비티")
        webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
        }
        var Text = Myapplication.prefs.getString("state","stable")
        Log.d(MainActivity.TAG, "$Text")


            when(Text){
                "stable" -> {
                    webView.loadUrl("https://www.youtube.com/watch?v=teikykTaW6I")

                }
                "waist" -> {
                    webView.loadUrl("https://www.youtube.com/watch?v=2YFY91RB05o&list=WL&index=2&t=29s")
                }
                "shoulder" -> {
                    webView.loadUrl( "https://www.youtube.com/watch?v=XT1dHyI86eQ&list=WL&index=3&t=3s")
                }
                "back" -> {
                    webView.loadUrl("https://www.youtube.com/watch?v=3zc1mGfA5kc&list=WL&index=1&t=4s")
                }
            }
        }


        // param 에다가 상황별로 URL 불러와서 넣기

        // 허리 영상 https://www.youtube.com/watch?v=2YFY91RB05o&list=WL&index=2

}