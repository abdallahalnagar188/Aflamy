package com.example.aflamy.di

import android.content.Context
import androidx.room.Room
import com.example.aflamy.presentation.adapter.wishlist.WishListAdapter
import com.example.data.local.dao.MovieDao
import com.example.data.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "wishlist_db"
        ).build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }

    @Provides
    fun provideWishListAdapter(): WishListAdapter {
        return WishListAdapter()
    }
}
