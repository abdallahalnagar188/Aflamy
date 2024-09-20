package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.roomEntitiy.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM wishlist_movies")
    fun getAllWishlistMovies(): List<MovieEntity>

    @Query("SELECT * FROM wishlist_movies WHERE id = :movieId")
    fun getMovieById(movieId: Int): MovieEntity?

    @Delete
     fun deleteMovie(movie: MovieEntity)
}


