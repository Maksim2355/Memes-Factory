package com.lumi.surfeducationproject.data.db

import androidx.room.*
import com.lumi.surfeducationproject.data.dto.local.DbMeme
import com.lumi.surfeducationproject.domain.model.Meme
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


@Dao
interface MemeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeme(meme: DbMeme)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMemeList(memes: List<DbMeme>)

    @Query("SELECT * FROM DbMeme")
    fun getAllMemes(): List<DbMeme>

    @Query("SELECT * FROM DbMeme WHERE is_local_user_created = 1")
    fun getAllUserMemes(): List<DbMeme>

    @Query("SELECT * FROM DbMeme WHERE id = :id")
    fun getMemeById(id: Int): DbMeme

    @Update
    fun update(meme: DbMeme)

    @Delete
    fun delete(meme: DbMeme)

}