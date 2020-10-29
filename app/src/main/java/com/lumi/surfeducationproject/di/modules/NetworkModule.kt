package com.lumi.surfeducationproject.di.modules

import com.lumi.surfeducationproject.BuildConfig
import com.lumi.surfeducationproject.data.api.AuthApi
import com.lumi.surfeducationproject.data.api.MemesApi
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule() {

    private val BASE_URL = "https://r2.mocker.surfstudio.ru/android_vsu/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            val builder = OkHttpClient().newBuilder()
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            builder.build()
        } else {
            OkHttpClient()
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // need for interceptors
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMemeApi(retrofit: Retrofit): MemesApi {
        return retrofit.create(MemesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }


}