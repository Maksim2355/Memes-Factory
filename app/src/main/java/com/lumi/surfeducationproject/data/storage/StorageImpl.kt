package com.lumi.surfeducationproject.data.storage

import com.lumi.surfeducationproject.data.db.MemeDao
import com.lumi.surfeducationproject.data.dto.local.MemeDbo
import com.lumi.surfeducationproject.data.dto.mappers.meme.MemeDataMapper
import com.lumi.surfeducationproject.domain.model.Meme
import javax.inject.Inject

class StorageImpl @Inject constructor(
    private val dao: MemeDao,
    private val mapper: MemeDataMapper<MemeDbo>
) : Storage {

    override fun insertUserMeme(memeUser: Meme) {
        val dbMeme = mapper.reverseTransform(memeUser)
        dbMeme.isLocalUserCreated = true
        dao.insertMeme(dbMeme)
    }

    override fun insertMemes(memeList: List<Meme>) {
        val dbMemeList = mapper.reverseTransformList(memeList)
        dao.insertMemeList(dbMemeList)
    }

    override fun getAllMemes(): List<Meme> {
        val dbMemeList = dao.getAllMemes()
        return mapper.transformList(dbMemeList)
    }

    override fun getUserMemes(): List<Meme> {
        val dbMemeList = dao.getAllUserMemes()
        return mapper.transformList(dbMemeList)
    }

    override fun removeMemes() {
        TODO("Not yet implemented")
    }


}