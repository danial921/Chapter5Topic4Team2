package com.example.chapter5topic4team2.model

import com.google.gson.annotations.SerializedName

data class UsersItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("id")
    val id: String
)