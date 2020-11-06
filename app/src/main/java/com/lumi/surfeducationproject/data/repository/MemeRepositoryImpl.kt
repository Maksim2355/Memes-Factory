package com.lumi.surfeducationproject.data.repository

import com.lumi.surfeducationproject.data.api.MemesApi
import com.lumi.surfeducationproject.data.dto.mappers.meme.MemeDataMapper
import com.lumi.surfeducationproject.data.dto.network.NetworkMeme
import com.lumi.surfeducationproject.data.storage.Storage
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.repository.MemeRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MemeRepositoryImpl @Inject constructor(
    private val memesApi: MemesApi,
    private val networkMapper: MemeDataMapper<NetworkMeme>,
    private val storage: Storage
) : MemeRepository {

    override fun getMemes(): Single<List<Meme>> = memesApi
        .getMemes()
        .map { networkMapper.transformList(it) }
        //TODO Подумать об ассинхронном добавлении в БД без костылей
        .doOnSuccess {
            storage.insertMemes(it).subscribe({
                println("Гуд жоп")
            }, { error ->
                println(error.javaClass)
            })
        }
        .onErrorReturn { storage.getAllMemes().blockingGet() }
        .subscribeOn(Schedulers.io())

    override fun getUserMemes(): Single<List<Meme>> = storage.getUserMemes()

    override fun addMeme(meme: Meme) {
        storage.insertUserMeme(meme)
            .subscribe({
                println("Гуд жоп")
            }, {
                println(it.javaClass)
            })
    }

}

