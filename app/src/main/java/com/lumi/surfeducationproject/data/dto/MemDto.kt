package com.lumi.surfeducationproject.data.dto

import java.io.Serializable

data class MemDto(val id: String,
                  val title: String,
                  val description: String,
                  val isFavorite: Boolean,
                  val createdDate: Int,
                  val photoUrl: String): Serializable {

}
