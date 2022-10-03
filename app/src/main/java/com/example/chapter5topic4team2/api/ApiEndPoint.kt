package com.example.chapter5topic4team2.api

import com.example.chapter5topic4team2.model.UsersItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndPoint {

    @GET("user")
    fun signIn():Call<MutableList<UsersItem>>

    @POST("user")
    fun signUp(@Body user : UsersItem):Call<UsersItem>
}