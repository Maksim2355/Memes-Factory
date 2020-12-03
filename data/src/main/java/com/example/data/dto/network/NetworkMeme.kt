package com.example.data.dto.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NetworkMeme(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("createdDate")
    val createdDate: Int,

    @SerializedName("description")
    val description: String,

    @SerializedName("isFavorite")
    val isFavorite: Boolean,

    @SerializedName("photoUrl")
    val photoUrl: String

) : Serializable
