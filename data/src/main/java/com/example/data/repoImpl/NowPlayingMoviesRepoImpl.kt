package com.example.data.repoImpl

import com.example.data.remote.Api
import com.example.domain.entity.dto.newPlaying.NewPlayingResponse
import com.example.domain.repo.repoRemote.NewPlayingMoviesRepo

class NowPlayingMoviesRepoImpl(private val api: Api) : NewPlayingMoviesRepo {
    override suspend fun getNewPlayingMovies(apiKey: String): NewPlayingResponse {
        return api.getNewPlayingMovies(apiKey)
    }
}
