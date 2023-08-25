package com.sananismayilov.noteapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.sananismayilov.noteapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
              val intent = Intent(this@SplashActivity,MainActivity::class.java)
              startActivity(intent)
            finish()
        },3000)
    }
}