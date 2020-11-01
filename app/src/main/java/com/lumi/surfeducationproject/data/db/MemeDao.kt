package com.lumi.surfeducationproject.data.db

import androidx.room.*
import com.lumi.surfeducationproject.domain.model.Meme
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


@Dao
interface MemeDao {

    @Insert
    fun insertMeme(meme: Meme)

    @Insert
    fun insertMemeList(memes: List<Meme>)

    @Query("SELECT * FROM DbMeme")
    fun getAllMemes(): Observable<Meme>

    @Query("SELECT * FROM DbMeme WHERE _id = :id")
    fun getMemeById(id: Int): Single<Meme>

    @Update
    fun update(meme: Meme)

    @Delete
    fun delete(meme: Meme)

}