package com.lumi.surfeducationproject.data.storage

import com.lumi.surfeducationproject.domain.model.Meme
import io.reactivex.rxjava3.core.Single

interface Storage {

    fun insertUserMeme(memeUser: Meme)

    fun insertMemes(memeList: List<Meme>)

    fun removeMemes()

    fun getAllMemes(): List<Meme>

    fun getUserMemes(): List<Meme>

}