package com.example.chapter5topic4team2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter5topic4team2.adapter.FilmAdapter
import com.example.chapter5topic4team2.databinding.ActivityHomeBinding
import com.example.chapter5topic4team2.model.FilmResponseItem
import com.example.chapter5topic4team2.util.Constant.Companion.SHARE_PREF
import com.example.chapter5topic4team2.viewmodel.FilmViewModel

class HomeActivity : AppCompatActivity(), FilmAdapter.FilmInterface {
    private lateinit var binding : ActivityHomeBinding

    private lateinit var adapter: FilmAdapter
    private val filmViewModel : FilmViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE)

        setDataRv()
        binding.apply {
            btnLogout.setOnClickListener {
                logout()
            }

            btnToAddFilms.setOnClickListener {
                moveToAddFilmActivity()
            }
        }
    }

    private fun setDataRv() {
        adapter = FilmAdapter(this)

        binding.apply {
            filmViewModel.showFilmList()
            filmViewModel.getLiveDataFilms().observe(this@HomeActivity){
                if (it != null){
                    progressBar.visibility = View.GONE
                    adapter.setData(it)
                }else{
                    progressBar.visibility = View.GONE
                    Toast.makeText(applicationContext, "Failed to load data", Toast.LENGTH_SHORT).show()
                }
                Log.d("DATAS","$it")
            }
            Log.d("DATASSSS","${filmViewModel.getLiveDataFilms().value}")

            rvFilm.adapter = adapter
            rvFilm.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun moveToAddFilmActivity() {
        startActivity(Intent(this, AddFilmActivity::class.java))
    }

    private fun logout() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        startActivity(Intent(this,LoginActivity::class.java))
    }

    override fun onItemClick(film: FilmResponseItem) {

    }

    override fun editFilm(film: FilmResponseItem) {
        val bundle = Bundle()
        val intent = Intent(this, EditActivity::class.java)
        bundle.putSerializable("dataFilmEdit", film)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun deleteFilm(id: String) {

    }
}