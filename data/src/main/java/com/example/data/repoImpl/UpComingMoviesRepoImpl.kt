package com.example.data.repoImpl

import com.example.data.remote.Api
import com.example.domain.entity.dto.upComing.UpComingResponse
import com.example.domain.repo.repoRemote.UpComingMoviesRepo

class UpComingMoviesRepoImpl(private val api: Api) : UpComingMoviesRepo {
    override suspend fun getUpComingMovies(apiKey: String): UpComingResponse {
        return api.getUpComingMovies(apiKey)
    }
}
