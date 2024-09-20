package com.example.domain.usecase.roomUescase

import com.example.domain.entity.models.MovieModelForLocal
import com.example.domain.repo.repoRoom.WishlistRepository

class GetWishlistMoviesUseCase(private val repository: WishlistRepository) {
    suspend operator fun invoke(): List<MovieModelForLocal> {
        return repository.getWishlistMovies()
    }
}
