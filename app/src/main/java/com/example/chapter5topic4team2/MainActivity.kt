package com.example.chapter5topic4team2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.chapter5topic4team2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSpalshScreen()
    }

    private fun setSpalshScreen(){
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
           startActivity(Intent(this,LoginActivity::class.java))
        },1500)
    }
}