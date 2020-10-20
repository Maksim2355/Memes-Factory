package com.lumi.surfeducationproject.data.model

import java.io.Serializable

data class Meme(val id: String,
                val title: String,
                val description: String,
                val isFavorite: Boolean,
                val createdDate: Int,
                val photoUrl: String): Serializable {

}
