package com.example.domain.usecase.remoteUseCase

import com.example.domain.repo.repoRemote.PopularMoviesInPagesRepo

class GetPopularMoviesInPages(private val popularMoviesRepo: PopularMoviesInPagesRepo) {
    suspend operator fun invoke(apiKey: String) = popularMoviesRepo.getPopularMoviesInPages(apiKey)
}
