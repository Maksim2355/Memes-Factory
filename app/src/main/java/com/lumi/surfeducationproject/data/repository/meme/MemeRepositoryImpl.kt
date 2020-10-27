package com.lumi.surfeducationproject.data.repository.meme

import com.lumi.surfeducationproject.data.api.MemesApi
import com.lumi.surfeducationproject.data.services.network.NetworkServiceImpl
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.repository.MemeRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class MemeRepositoryImpl: MemeRepository {

    private val memesApi: MemesApi = NetworkServiceImpl.getMemeApi()

    override fun getMemes(): Single<List<Meme>> {
        TODO("Not yet implemented")
    }

    override fun addMeme(meme: Meme): Completable {
        TODO("Not yet implemented")
    }

}