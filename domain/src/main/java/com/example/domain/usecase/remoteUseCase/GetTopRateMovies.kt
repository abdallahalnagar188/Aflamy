package com.example.domain.usecase.remoteUseCase

import com.example.domain.repo.repoRemote.TopRateMoviesRepo

class GetTopRateMovies(private val topRateMovies: TopRateMoviesRepo)  {
    suspend operator fun invoke(apiKey: String) = topRateMovies.getTopRateMovies(apiKey)
}