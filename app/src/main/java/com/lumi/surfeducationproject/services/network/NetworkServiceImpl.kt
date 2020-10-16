package com.lumi.surfeducationproject.services.network

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkServiceImpl: NetworkService {

    private const val BASE_URL = "https://r2.mocker.surfstudio.ru/android_vsu/"

    private var client: OkHttpClient? = null
    private var retrofit: Retrofit? = null
    private var api: MemesApi? = null


    private fun getClient(): OkHttpClient {
        if (client == null) {
            val builder = OkHttpClient().newBuilder()
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            client = builder.build()
        }
        return client!!
    }

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL) // need for interceptors
                .client(getClient())
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