package com.example.domain.repo.repoRoom

import com.example.domain.entity.models.MovieModelForLocal

interface WishlistRepository {
    suspend fun addMovieToWishlist(movie: MovieModelForLocal)
    suspend fun removeMovieFromWishlist(movie: MovieModelForLocal)
    suspend fun getWishlistMovies(): List<MovieModelForLocal>
    suspend fun isMovieBookmarked(movieId: Int): Boolean
}
