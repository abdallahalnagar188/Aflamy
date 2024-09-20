package com.example.data.repoImpl

import com.example.data.remote.Api
import com.example.domain.entity.dto.popularMovies.PopularMoviesResponse
import com.example.domain.repo.repoRemote.PopularMoviesRepo

class PopularMoviesRepoImpl(private val api: Api) : PopularMoviesRepo {
    override suspend fun getPopularMovies(apiKey: String): PopularMoviesResponse {
        return api.getPopularMovies(apiKey)
    }



}
