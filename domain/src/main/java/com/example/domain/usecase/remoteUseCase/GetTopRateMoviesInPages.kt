package com.example.domain.usecase.remoteUseCase

import com.example.domain.repo.repoRemote.PopularMoviesInPagesRepo
import com.example.domain.repo.repoRemote.TopRateMoviesInPagesRepo

class GetTopRateMoviesInPages(private val topRateMoviesRepo: TopRateMoviesInPagesRepo) {
    suspend operator fun invoke(apiKey: String) = topRateMoviesRepo.getTopRateMoviesInPages(apiKey)
}
