package com.example.chapter5topic4team2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Film(
    val name: String,
    val image: String,
    val director: String,
    val description: String
) : Serializable