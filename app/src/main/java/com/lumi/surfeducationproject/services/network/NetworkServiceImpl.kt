package com.lumi.surfeducationproject.services.network

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkServiceImpl: NetworkService {

    private const val BASE_URL = "https://virtserver.swaggerhub.com/AndroidSchool/SurfAndroidSchool/1.0.0/"

    private var retrofit: Retrofit? = null
    private var api: MemesApi? = null

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL) // need for interceptors
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }
        return retrofit!!
    }

    override fun getApi(): MemesApi {
        if (api == null) {
            api = getRetrofit().create(MemesApi::class.java)
        }
        return api!!
    }



}