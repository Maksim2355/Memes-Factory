package com.example.data.storage

import com.example.domain.model.Meme
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface Storage {

    fun insertUserMeme(memeUser: Meme): Completable

    fun insertMemes(memeList: List<Meme>)

    fun removeMemes(): Completable

    fun getAllMemes(): Single<List<Meme>>

    fun getUserMemes(): Single<List<Meme>>

}