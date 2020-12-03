package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dto.local.MemeDbo


@Database(entities = [MemeDbo::class], version = 5, exportSchema = false)
abstract class MemeDatabase: RoomDatabase() {
    abstract fun memeDao(): MemeDao
}