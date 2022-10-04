package com.example.chapter5topic4team2.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilmResponseItem(
    @SerializedName("date")
    val date: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String
) : Serializable