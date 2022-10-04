package com.example.chapter5topic4team2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapter5topic4team2.databinding.ActivityAddFilmBinding

class AddFilmActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackAdd.setOnClickListener {
            onBackPressed()
        }

        addFilm()
    }

    private fun addFilm() {

    }
}