package com.lumi.surfeducationproject.domain.model

data class Meme(
    val id: Int,
    val title: String,
    val createdDate: Int,
    val photoUrl: String,
    val isFavourite: Boolean
)