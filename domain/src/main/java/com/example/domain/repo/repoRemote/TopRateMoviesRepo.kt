package com.example.domain.repo.repoRemote

import com.example.domain.entity.dto.topRate.TopRateMoviesResponse

interface TopRateMoviesRepo {
    suspend fun getTopRateMovies(apiKey: String): TopRateMoviesResponse
}