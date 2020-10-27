package com.lumi.surfeducationproject.data.services.network

import com.lumi.surfeducationproject.BuildConfig
import com.lumi.surfeducationproject.data.api.AuthApi
import com.lumi.surfeducationproject.data.api.MemesApi
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkServiceImpl : NetworkService {

    private const val BASE_URL = "https://r2.mocker.surfstudio.ru/android_vsu/"

    private var client: OkHttpClient? = null
    private var retrofit: Retrofit? = null

    private var authApi: AuthApi? = null
    private var memeApi: MemesApi? = null


    private fun getClient(): OkHttpClient {
        if (client == null) {
            client = if (BuildConfig.DEBUG) {
                val builder = OkHttpClient().newBuilder()
                builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                builder.build()
            } else {
                OkHttpClient()
            }
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

    override fun getMemeApi(): MemesApi {
        if (memeApi == null) {
            memeApi = getRetrofit().create(MemesApi::class.java)
        }
        return memeApi!!
    }

    override fun getAuthApi(): AuthApi {
        if (memeApi == null) {
            authApi = getRetrofit().create(AuthApi::class.java)
        }
        return authApi!!
    }


}