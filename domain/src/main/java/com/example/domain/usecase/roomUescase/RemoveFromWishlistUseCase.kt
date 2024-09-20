package com.example.domain.usecase.roomUescase

import com.example.domain.entity.models.MovieModel
import com.example.domain.entity.models.MovieModelForLocal
import com.example.domain.repo.repoRoom.WishlistRepository

class RemoveFromWishlistUseCase(private val repository: WishlistRepository) {
    suspend operator fun invoke(movie: MovieModelForLocal) {
        repository.removeMovieFromWishlist(movie)
    }
}
