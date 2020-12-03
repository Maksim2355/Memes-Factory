package com.example.data.dto.mappers.meme

import com.example.domain.model.Meme


interface MemeDataMapper<T> {

    fun transform(meme: T): Meme

    fun transformList(list: List<T>): List<Meme>

    fun reverseTransform(meme: Meme): T

    fun reverseTransformList(list: List<Meme>): List<T>
}