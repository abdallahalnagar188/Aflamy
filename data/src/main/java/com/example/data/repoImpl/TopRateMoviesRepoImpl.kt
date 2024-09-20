package com.example.data.repoImpl

import com.example.data.remote.Api
import com.example.domain.entity.dto.topRate.TopRateMoviesResponse
import com.example.domain.repo.repoRemote.TopRateMoviesRepo

class TopRateMoviesRepoImpl(private val api: Api) : TopRateMoviesRepo {
    override suspend fun getTopRateMovies(apiKey: String): TopRateMoviesResponse {
        return api.getTopRateMovies(apiKey)
    }
}
