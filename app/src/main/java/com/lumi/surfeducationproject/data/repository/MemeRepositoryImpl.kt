package com.lumi.surfeducationproject.data.repository

import com.lumi.surfeducationproject.data.api.MemesApi
import com.lumi.surfeducationproject.data.dto.mappers.MemeDtoDataMapper
import com.lumi.surfeducationproject.data.services.network.NetworkServiceImpl
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.repository.MemeRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MemeRepositoryImpl: MemeRepository {

    private val memesApi: MemesApi = NetworkServiceImpl.getMemeApi()
    private val mapper: MemeDtoDataMapper = MemeDtoDataMapper()

    override fun getMemes(): Single<List<Meme>> {
        return memesApi.getMemes()
            .map { mapper.transformList(it) }
            // В случае неудачного запроса, возвращаем список из базы данных
            // .onErrorReturn {}
    }

    override fun addMeme(meme: Meme): Completable {
        TODO("Not yet implemented")
    }

}