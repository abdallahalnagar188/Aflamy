package com.example.domain.usecase.remoteUseCase

import com.example.domain.repo.repoRemote.PopularMoviesRepo

class GetPopularMovies(private val popularMoviesRepo: PopularMoviesRepo) {
    suspend operator fun invoke(apiKey: String) = popularMoviesRepo.getPopularMovies(apiKey)

}
