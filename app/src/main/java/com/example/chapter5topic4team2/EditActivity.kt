package com.example.chapter5topic4team2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chapter5topic4team2.databinding.ActivityEditBinding
import com.example.chapter5topic4team2.model.FilmResponseItem
import com.example.chapter5topic4team2.viewmodel.FilmViewModel

class EditActivity : AppCompatActivity() {
    lateinit var binding : ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getData = intent.getSerializableExtra("dataFilmEdit") as FilmResponseItem

        binding.dataFilmsUpdate = getData

        binding.btnUpdate.setOnClickListener{

            val id = getData.id
            val name = binding.etName.text.toString()
            val image = binding.etImage.text.toString()
            val director = binding.etDirector.text.toString()
            val desc = binding.etDesc.text.toString()
            Log.d("infoid",id)
            updateFilm(id.toInt(),name,image,director, desc)
            finish()

        }
    }

    fun updateFilm (id : Int,name : String, image : String,director : String, desc : String){
        var viewModel = ViewModelProvider(this).get(FilmViewModel::class.java)
        viewModel.updateDataFilm(id , name , image, director , desc )
        viewModel.updLivedataFilm().observe(this, Observer {
            if(it != null){
                Toast.makeText(this, "Update Data Success", Toast.LENGTH_SHORT).show()
                Log.d("editFilm", it.toString())
            }
        })
    }

}