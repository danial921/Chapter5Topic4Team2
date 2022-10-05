package com.example.chapter5topic4team2.viewmodel

import android.util.Log
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
    private val liveDataDetailFilms : MutableLiveData<FilmResponseItem> = MutableLiveData()
    private val postDataFilm : MutableLiveData<FilmResponseItem> = MutableLiveData()
    private val putDataFilm : MutableLiveData<List<FilmResponseItem>> = MutableLiveData()
    private val deleteDataFilm : MutableLiveData<Int> = MutableLiveData()


    fun getLiveDataFilms() : MutableLiveData<List<FilmResponseItem>> = liveDataFilms
    fun postLiveDataFilms() : MutableLiveData<FilmResponseItem> = postDataFilm
    fun liveDataDetailFilms() : MutableLiveData<FilmResponseItem> = liveDataDetailFilms
    fun updLivedataFilm(): MutableLiveData<List<FilmResponseItem>> = putDataFilm
    fun delLiveDataFilm() : MutableLiveData<Int> = deleteDataFilm


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
                    Log.d("onFailure","${t.message}")

                }

            })
    }

    fun callDetailApifilm(id: Int){
        ApiService.instance.getDetailFilm(id)
            .enqueue(object : Callback<FilmResponseItem>{
                override fun onResponse(
                    call: Call<FilmResponseItem>,
                    response: Response<FilmResponseItem>
                ) {
                    if (response.isSuccessful){
                        liveDataDetailFilms.postValue(response.body())
                    }else{
                        liveDataDetailFilms.postValue(null)
                    }
                }

                override fun onFailure(call: Call<FilmResponseItem>, t: Throwable) {
                    liveDataDetailFilms.postValue(null)
                }

            })
    }

    fun addDataFilm(name : String, image : String, director : String, description : String){
        ApiService.instance.addFilm(Film(name, image, director, description))
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

    fun updateDataFilm(id : Int, name : String, image : String, director : String, description : String){
        ApiService.instance.updateFilm(id, Film(name, image, director, description))
            .enqueue(object : Callback<List<FilmResponseItem>>{
                override fun onResponse(
                    call: Call<List<FilmResponseItem>>,
                    response: Response<List<FilmResponseItem>>
                ) {
                    if (response.isSuccessful){
                        putDataFilm.postValue(response.body())
                    }else{
                        putDataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<FilmResponseItem>>, t: Throwable) {
                    postDataFilm.postValue(null)
                }

            })
    }

    fun deleteDataFilm(id: Int){
        ApiService.instance.deleteFilm(id)
            .enqueue(object : Callback<Int>{
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if (response.isSuccessful){
                        deleteDataFilm.postValue(response.body())
                    }else{
                        deleteDataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    deleteDataFilm.postValue(null)
                }

            })
    }

}