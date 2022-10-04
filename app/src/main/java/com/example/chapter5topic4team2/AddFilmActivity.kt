package com.example.chapter5topic4team2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chapter5topic4team2.databinding.ActivityAddFilmBinding
import com.example.chapter5topic4team2.viewmodel.FilmViewModel

class AddFilmActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddFilmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackAdd.setOnClickListener {
            onBackPressed()
        }

       binding.btnAddFilm.setOnClickListener {
           addFilm()
       }
    }

    private fun addFilm() {
        var judul = binding.etJudulFilm.text.toString()
        var direct = binding.etDirector.text.toString()
        var desc = binding.etDesctiptionFilm.text.toString()
        var imgFilm = binding.etImageFilm.text.toString()

        val viewModel = ViewModelProvider(this).get(FilmViewModel::class.java)
        viewModel.addDataFilm(judul, direct, desc, imgFilm)
        viewModel.postLiveDataFilms().observe(this , Observer {
            if (it != null){
                Toast.makeText(this, "Add Data Successfull", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                Log.d("addFilm", it.toString())
            }
        })


    }
}