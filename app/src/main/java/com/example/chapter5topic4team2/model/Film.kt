package com.example.chapter5topic4team2.model

import com.google.gson.annotations.SerializedName

data class Film(
    val date: String,
    val name: String,
    val image: String,
    val director: String,
    val description: String
)

data class FilmAdd(
    val name: String,
    val image: String,
    val director: String,
    val description: String
)
