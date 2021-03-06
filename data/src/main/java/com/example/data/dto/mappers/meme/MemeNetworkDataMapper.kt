package com.example.data.dto.mappers.meme


import com.example.data.dto.network.NetworkMeme
import com.example.domain.model.Meme
import java.util.ArrayList
import javax.inject.Inject

class MemeNetworkDataMapper: MemeDataMapper<NetworkMeme> {


    override fun transform(meme: NetworkMeme): Meme {
        return Meme(
            meme.id.toInt(),
            meme.title,
            meme.createdDate,
            meme.description,
            meme.isFavorite,
            meme.photoUrl
        )
    }

    override fun transformList(list: List<NetworkMeme>): List<Meme> {
        val listMeme = ArrayList<Meme>()
        for (memeNetwork in list) {
            listMeme.add(
                Meme(
                    memeNetwork.id.toInt(),
                    memeNetwork.title,
                    memeNetwork.createdDate,
                    memeNetwork.description,
                    memeNetwork.isFavorite,
                    memeNetwork.photoUrl
                )
            )

        }
        return listMeme
    }

    override fun reverseTransform(meme: Meme): NetworkMeme {
        TODO("Not yet implemented")
    }

    override fun reverseTransformList(list: List<Meme>): List<NetworkMeme> {
        TODO("Not yet implemented")
    }


}
