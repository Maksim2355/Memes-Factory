package com.lumi.surfeducationproject.domain.repository

import com.lumi.surfeducationproject.domain.model.Meme
import io.reactivex.rxjava3.core.Single

interface MemeRepository {

    fun getMemes(): Single<List<Meme>>

    fun getUserMemes(): Single<List<Meme>>

    fun addMeme(meme: Meme)

}