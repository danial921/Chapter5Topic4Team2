package com.example.chapter5topic4team2.api

import com.example.chapter5topic4team2.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    @GET("user")
    fun signIn():Call<ArrayList<UsersItem>>

    @POST("user")
    fun signUp(@Body user : UsersItem):Call<UsersItem>

    @GET("film/{id}")
    fun getDetailFilm(@Path("id") id : Int): Call<FilmResponseItem>

    // endpoint film
    @GET("film")
    fun getAllfilm() : Call<List<FilmResponseItem>>

    @POST("film")
    fun addFilm(@Body film : Film) : Call<FilmResponseItem>

    @PUT("film/{id}")
    fun updateFilm(@Path("id") id : Int, @Body request : Film ) : Call<List<FilmResponseItem>>

    @DELETE("film/{id}")
    fun deleteFilm(@Path("id") id : Int) : Call<Int>
}