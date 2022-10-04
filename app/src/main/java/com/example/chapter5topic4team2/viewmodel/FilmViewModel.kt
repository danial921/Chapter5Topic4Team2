package com.example.chapter5topic4team2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chapter5topic4team2.api.ApiService
import com.example.chapter5topic4team2.model.Film
import com.example.chapter5topic4team2.model.FilmResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmViewModel : ViewModel() {

    private val liveDataFilms : MutableLiveData<List<FilmResponseItem>> = MutableLiveData()
    private val postDataFilm : MutableLiveData<FilmResponseItem> = MutableLiveData()

    fun getLiveDataFilms() : MutableLiveData<List<FilmResponseItem>> = liveDataFilms
    fun postLiveDataFilms() : MutableLiveData<FilmResponseItem> = postDataFilm

    fun showFilmList(){
        ApiService.instance.getAllfilm()
            .enqueue(object : Callback<List<FilmResponseItem>>{
                override fun onResponse(
                    call: Call<List<FilmResponseItem>>,
                    response: Response<List<FilmResponseItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataFilms.postValue(response.body())
                    }else{
                        liveDataFilms.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<FilmResponseItem>>, t: Throwable) {
                    liveDataFilms.postValue(null)
                }

            })
    }

    fun addDataFilm(date : String, name : String, image : String, director : String, description : String){
        ApiService.instance.addFilm(Film(date, name, image, director, description))
            .enqueue(object  : Callback<FilmResponseItem>{
                override fun onResponse(
                    call: Call<FilmResponseItem>,
                    response: Response<FilmResponseItem>
                ) {
                    if (response.isSuccessful){
                        postDataFilm.postValue(response.body())
                    }else{
                        postDataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<FilmResponseItem>, t: Throwable) {
                    postDataFilm.postValue(null)
                }

            })
    }

}