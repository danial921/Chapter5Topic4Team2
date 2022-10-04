package com.example.chapter5topic4team2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chapter5topic4team2.databinding.ActivityDetailfilmBinding
import com.example.chapter5topic4team2.viewmodel.FilmViewModel
import kotlin.math.log

class DetailfilmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailfilmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailfilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDetailFilm()
    }

    private fun getDetailFilm(){
        val a = intent.getStringExtra("id")!!.toInt()
        val viewModel = ViewModelProvider(this).get(FilmViewModel::class.java)
        viewModel.callDetailApifilm(a)
        viewModel.liveDataDetailFilms().observe(this, Observer {

            binding.apply {
                tvDeskripsiFilm.text = it.description
                tvdirectorFilm.text = it.director
                tvnamaFilm.text = it.name
            }
        })
    }
}