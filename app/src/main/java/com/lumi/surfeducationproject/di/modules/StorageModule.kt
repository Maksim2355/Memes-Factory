package com.lumi.surfeducationproject.di.modules

import android.content.Context
import androidx.room.Room
import com.lumi.surfeducationproject.data.db.MemeDao
import com.lumi.surfeducationproject.data.db.MemeDatabase
import com.lumi.surfeducationproject.data.dto.local.DbMeme
import com.lumi.surfeducationproject.data.dto.mappers.MemeDataMapper
import com.lumi.surfeducationproject.data.dto.mappers.MemeDbDataMapper
import com.lumi.surfeducationproject.data.storage.Storage
import com.lumi.surfeducationproject.data.storage.StorageImpl
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
    fun provideDbMemeMapper(): MemeDataMapper<DbMeme> = MemeDbDataMapper()

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
    fun provideStorage(memeDao: MemeDao, mapperDb: MemeDataMapper<DbMeme>): Storage =
        StorageImpl(memeDao, mapperDb)

}