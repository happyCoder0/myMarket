package com.happycoder.mymarket

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.happycoder.mymarket.util.ApiHelper

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val productListViewModel: ProductListViewModel by viewModels()

        Handler(Looper.getMainLooper()).post {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }
}