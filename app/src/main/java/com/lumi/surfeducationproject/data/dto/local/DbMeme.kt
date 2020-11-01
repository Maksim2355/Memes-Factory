package com.lumi.surfeducationproject.data.dto.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class DbMeme(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "created_date")
    val createdDate: Int,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean,

    @ColumnInfo(name = "photo_url")
    val photoUrl: String,

    @ColumnInfo(name = "is_local_user_created")
    var isLocalUserCreated: Boolean = false
)