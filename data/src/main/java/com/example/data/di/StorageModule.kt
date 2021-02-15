package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.db.MemeDao
import com.example.data.db.MemeDatabase
import com.example.data.dto.local.MemeDbo
import com.example.data.dto.mappers.UserDtoDataMapper
import com.example.data.dto.mappers.meme.MemeDataMapper
import com.example.data.dto.mappers.meme.MemeDbDataMapper
import com.example.data.dto.mappers.meme.MemeNetworkDataMapper
import com.example.data.dto.network.NetworkMeme
import com.example.data.storage.Storage
import com.example.data.storage.StorageImpl

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class StorageModule {

    companion object {
        private const val nameDatabase = "MEME_DATABASE"
    }

    @Provides
    @Singleton
    fun provideUserMapper(): UserDtoDataMapper {
        return UserDtoDataMapper()
    }

    @Provides
    @Singleton
    fun provideMemeDataMapper(): MemeDataMapper<NetworkMeme> {
        return MemeNetworkDataMapper()
    }


    @Provides
    @Singleton
    fun provideDbMemeMapper(): MemeDataMapper<MemeDbo> = MemeDbDataMapper()

    @Provides
    @Singleton
    fun provideDatabase(context: Context): MemeDatabase =
        Room.databaseBuilder(context, MemeDatabase::class.java, nameDatabase)
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun provideMemeDao(database: MemeDatabase): MemeDao = database.memeDao()


    @Provides
    @Singleton
    fun provideStorage(memeDao: MemeDao, mapperDbo: MemeDataMapper<MemeDbo>): Storage =
        StorageImpl(memeDao, mapperDbo)

}