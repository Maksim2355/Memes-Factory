package com.lumi.surfeducationproject.data.storage

import com.lumi.surfeducationproject.data.db.MemeDao
import com.lumi.surfeducationproject.data.dto.local.MemeDbo
import com.lumi.surfeducationproject.data.dto.mappers.meme.MemeDataMapper
import com.lumi.surfeducationproject.domain.model.Meme
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class StorageImpl @Inject constructor(
    private val dao: MemeDao,
    private val mapper: MemeDataMapper<MemeDbo>
) : Storage {

    override fun insertUserMeme(memeUser: Meme): Completable = Completable.fromCallable {
            val dbMeme = mapper.reverseTransform(memeUser)
            dbMeme.isLocalUserCreated = true
            dao.insertMeme(dbMeme)
        }.subscribeOn(Schedulers.io())


    override fun insertMemes(memeList: List<Meme>): Completable = Completable.fromCallable {
        val dbMemeList = mapper.reverseTransformList(memeList)
        dao.insertMemeList(dbMemeList)
    }.subscribeOn(Schedulers.io())

    override fun getAllMemes(): Single<List<Meme>> = Single.fromCallable {
        val dbMemeList = dao.getAllMemes()
        mapper.transformList(dbMemeList)
    }.subscribeOn(Schedulers.io())


    override fun getUserMemes(): Single<List<Meme>> = Single.fromCallable {
        val dbMemeList = dao.getAllUserMemes()
        mapper.transformList(dbMemeList)
    }.subscribeOn(Schedulers.io())

    override fun removeMemes(): Completable {
        TODO("Not yet implemented")
    }


}