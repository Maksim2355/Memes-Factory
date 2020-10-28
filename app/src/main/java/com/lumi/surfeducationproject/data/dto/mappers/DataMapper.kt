package com.lumi.surfeducationproject.data.dto.mappers

interface DataMapper<T, O> {

    fun transform(data: T): O

    fun transform(listData: List<T>): List<O>

}