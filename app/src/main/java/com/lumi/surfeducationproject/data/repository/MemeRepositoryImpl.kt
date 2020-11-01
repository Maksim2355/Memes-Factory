package com.lumi.surfeducationproject.data.repository

import com.lumi.surfeducationproject.data.api.MemesApi
import com.lumi.surfeducationproject.data.db.MemeDao
import com.lumi.surfeducationproject.data.dto.mappers.MemeDtoDataMapper
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.repository.MemeRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MemeRepositoryImpl @Inject constructor(
    private val memesApi: MemesApi,
    private val memeDao: MemeDao,
    private val mapper: MemeDtoDataMapper
    ) : MemeRepository {


    override fun getMemes(): Single<List<Meme>> {
        return memesApi.getMemes()
            .map { mapper.transformList(it) }
//            .onErrorReturn {
//                memeDao.getAllMemes().map {  }
//            }
    }

    override fun addMeme(meme: Meme): Completable {
        TODO("Not yet implemented")
    }

}