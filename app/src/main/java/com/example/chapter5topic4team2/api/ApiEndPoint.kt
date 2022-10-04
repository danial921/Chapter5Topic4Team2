package com.example.chapter5topic4team2.api

import com.example.chapter5topic4team2.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    @GET("user")
    fun signIn():Call<ArrayList<UsersItem>>

    @POST("user")
    fun signUp(@Body user : UsersItem):Call<UsersItem>

    // endpoint film
    @GET("film")
    fun getAllfilm() : Call<List<FilmResponseItem>>

    @POST("film")
    fun addFilm(@Body film : FilmAdd) : Call<FilmResponseItem>

    @PUT("film/{id}")
    fun updateFilm(@Path("id") id : Int, @Body request : Film ): Call<List<PutFilmResponseItem>>
}