package com.lumi.surfeducationproject.di.modules

import android.content.Context
import androidx.room.Room
import com.lumi.surfeducationproject.data.db.MemeDao
import com.lumi.surfeducationproject.data.db.MemeDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class StorageModule {

    private val NAME_DATABASE = "meme_database"

    @Provides
    @Singleton
    fun provideDatabase(context: Context): MemeDatabase{
        return Room.databaseBuilder(context, MemeDatabase::class.java, NAME_DATABASE).build()
    }

    @Provides
    @Singleton
    fun provideMemeDao(database: MemeDatabase): MemeDao{
        return database.memeDao()
    }



}