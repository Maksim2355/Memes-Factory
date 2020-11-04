package com.lumi.surfeducationproject.data.repository

import com.lumi.surfeducationproject.data.api.MemesApi
import com.lumi.surfeducationproject.data.db.MemeDao
import com.lumi.surfeducationproject.data.dto.local.DbMeme
import com.lumi.surfeducationproject.data.dto.mappers.MemeDataMapper
import com.lumi.surfeducationproject.data.dto.network.NetworkMeme
import com.lumi.surfeducationproject.data.exceptions.EmptyMemesDatabaseException
import com.lumi.surfeducationproject.data.storage.Storage
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.repository.MemeRepository
import com.lumi.surfeducationproject.exceptions.NETWORK_EXCEPTIONS
import io.reactivex.rxjava3.core.Completable
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
        .subscribeOn(Schedulers.io())
        .map { networkMapper.transformList(it) }
        .doOnSuccess { storage.insertMemes(it) }
        //.onErrorReturn { storage.getAllMemes().blockingGet() }

    override fun getUserMemes(): Single<List<Meme>> = storage.getUserMemes()

    override fun addMeme(meme: Meme) = storage.insertUserMeme(meme)

}