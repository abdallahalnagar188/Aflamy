package com.example.data.repoImpl

import com.example.data.local.dao.MovieDao
import com.example.data.local.roomEntitiy.MovieEntity
import com.example.domain.entity.models.MovieModelForLocal
import com.example.domain.repo.repoRoom.WishlistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WishlistRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao
) : WishlistRepository {

    override suspend fun addMovieToWishlist(movie: MovieModelForLocal) {
        withContext(Dispatchers.IO) {
            movieDao.insertMovie(movie.toEntity())
        }
    }

    override suspend fun removeMovieFromWishlist(movie: MovieModelForLocal) {
        withContext(Dispatchers.IO) {  // Ensure this runs on a background thread
            movieDao.deleteMovie(movie.toEntity())
        }
    }

    override suspend fun getWishlistMovies(): List<MovieModelForLocal> {
        return withContext(Dispatchers.IO) {  // Ensure this runs on a background thread
            movieDao.getAllWishlistMovies().map { it.toDomainModel() }
        }
    }

    override suspend fun isMovieBookmarked(movieId: Int): Boolean {
        return withContext(Dispatchers.IO) {
            movieDao.getMovieById(movieId) != null
        }
    }

    private fun MovieModelForLocal.toEntity(): MovieEntity {
        return MovieEntity(id, title, posterPath, releaseDate, voteAverage)
    }

    private fun MovieEntity.toDomainModel(): MovieModelForLocal {
        return MovieModelForLocal(
            id = id,
            title = title,
            posterPath = posterPath,
            releaseDate = releaseDate,
            voteAverage = voteAverage,

        )
    }
}
