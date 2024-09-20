package com.example.domain.repo.repoRemote

import com.example.domain.entity.dto.popularMovies.PopularMoviesResponse

interface PopularMoviesRepo {
    suspend fun getPopularMovies(apiKey: String): PopularMoviesResponse


}