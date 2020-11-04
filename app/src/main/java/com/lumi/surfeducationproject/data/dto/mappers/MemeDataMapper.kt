package com.lumi.surfeducationproject.data.dto.mappers

import com.lumi.surfeducationproject.domain.model.Meme

interface MemeDataMapper<T> {

    fun transform(meme: T): Meme

    fun transformList(list: List<T>): List<Meme>

    fun reverseTransform(meme: Meme): T

    fun reverseTransformList(list: List<Meme>): List<T>
}