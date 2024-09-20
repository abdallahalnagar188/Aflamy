package com.example.domain.repo.repoRemote

import com.example.domain.entity.dto.newPlaying.NewPlayingResponse

interface NewPlayingMoviesRepo {
    suspend fun getNewPlayingMovies(apiKey: String): NewPlayingResponse
}