package com.example.chapter5topic4team2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter5topic4team2.adapter.FilmAdapter
import com.example.chapter5topic4team2.databinding.ActivityHomeBinding
import com.example.chapter5topic4team2.model.FilmResponseItem
import com.example.chapter5topic4team2.viewmodel.FilmViewModel

class HomeActivity : AppCompatActivity(), FilmAdapter.FilmInterface {
    private lateinit var binding : ActivityHomeBinding

    private lateinit var adapter: FilmAdapter
    private val filmViewModel : FilmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDataRv()
    }

    private fun setDataRv() {
        adapter = FilmAdapter(this)

        binding.apply {
            filmViewModel.showFilmList()
            filmViewModel.getLiveDataFilms().observe(this@HomeActivity){
                adapter.setData(it)
                Log.d("DATAS","$it")
            }
            Log.d("DATASSSS","${filmViewModel.getLiveDataFilms().value}")

            rvFilm.adapter = adapter
            rvFilm.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onItemClick(film: FilmResponseItem) {

    }
}