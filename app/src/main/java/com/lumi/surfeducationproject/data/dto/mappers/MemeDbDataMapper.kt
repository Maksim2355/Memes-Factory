package com.lumi.surfeducationproject.data.dto.mappers

import com.lumi.surfeducationproject.data.dto.local.DbMeme
import com.lumi.surfeducationproject.domain.model.Meme
import java.util.ArrayList

class MemeDbDataMapper : MemeDataMapper<DbMeme> {

    override fun transform(meme: DbMeme) = Meme(
        id = meme.id!!,
        title = meme.title,
        createdDate = meme.createdDate,
        description = meme.description,
        isFavorite = meme.isFavorite,
        photoUrl = meme.photoUrl
    )


    override fun transformList(list: List<DbMeme>): List<Meme> {
        val listMeme = ArrayList<Meme>()
        for (meme in list) {
            listMeme.add(
                Meme(
                    id = meme.id!!,
                    title = meme.title,
                    createdDate = meme.createdDate,
                    description = meme.description,
                    isFavorite = meme.isFavorite,
                    photoUrl = meme.photoUrl
                )
            )

        }
        return listMeme
    }

    override fun reverseTransform(meme: Meme): DbMeme = DbMeme(
        title = meme.title,
        createdDate = meme.createdDate,
        description = meme.description,
        isFavorite = meme.isFavorite,
        photoUrl = meme.photoUrl
        )

    override fun reverseTransformList(list: List<Meme>): List<DbMeme> {
        val listMeme = ArrayList<DbMeme>()
        for (meme in list) {
            listMeme.add(
                DbMeme(
                    title = meme.title,
                    createdDate = meme.createdDate,
                    description = meme.description,
                    isFavorite = meme.isFavorite,
                    photoUrl = meme.photoUrl
                )
            )
        }
        return listMeme
    }
}