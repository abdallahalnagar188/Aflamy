package com.example.domain.repo.repoRemote

import com.example.domain.entity.dto.upComing.UpComingResponse

interface UpComingMoviesRepo {
    suspend fun getUpComingMovies(apiKey: String): UpComingResponse
}