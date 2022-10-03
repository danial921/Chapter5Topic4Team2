package com.example.chapter5topic4team2.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private const val BASE_URL = "https://6254434289f28cf72b5aed04.mockapi.io/"

    val instance: ApiEndPoint by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiEndPoint::class.java)
    }
}