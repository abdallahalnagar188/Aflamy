package com.example.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.MovieDao
import com.example.data.local.roomEntitiy.MovieEntity

@Database(entities = [MovieEntity::class], version = 2)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
