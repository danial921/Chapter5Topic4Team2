package com.example.chapter5topic4team2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.chapter5topic4team2.databinding.ActivityMainBinding
import com.example.chapter5topic4team2.util.Constant

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(Constant.SHARE_PREF, Context.MODE_PRIVATE)

        setSplashScreen()
    }

    private fun setSplashScreen(){
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            isLogin()
        },3000)
    }

    private fun isLogin(){
        val id = sharedPreferences.getString(Constant.ID,"undefined")
        Toast.makeText(this, "$id", Toast.LENGTH_SHORT).show()
        if(!id.equals("undefined")){
            startActivity(Intent(this,HomeActivity::class.java))
        }else{
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}