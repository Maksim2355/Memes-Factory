package com.lumi.surfeducationproject.data.dto.mappers

import com.lumi.surfeducationproject.data.dto.network.NetworkMeme
import com.lumi.surfeducationproject.data.dto.network.NetworkUserResponse
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.model.User
import java.util.ArrayList

class MemeDtoDataMapper {


    fun transform(memeNetwork: NetworkMeme): Meme {
        return Meme(
            memeNetwork.id.toInt(),
            memeNetwork.title,
            memeNetwork.createdDate,
            memeNetwork.description,
            memeNetwork.isFavorite,
            memeNetwork.photoUrl
        )
    }

    fun transformList(memeNetworkList: List<NetworkMeme>): List<Meme> {
        val list = ArrayList<Meme>()
        for (memeNetwork in memeNetworkList){
            list.add(Meme(
                memeNetwork.id.toInt(),
                memeNetwork.title,
                memeNetwork.createdDate,
                memeNetwork.description,
                memeNetwork.isFavorite,
                memeNetwork.photoUrl
            ))

        }
        return list
    }


}
