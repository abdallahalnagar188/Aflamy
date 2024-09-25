package com.example.data.repoImpl

import com.example.data.remote.Api
import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.newPlaying.NowPlayingMoviesDto
import com.example.domain.entity.safeApiCall
import com.example.domain.repo.repoRemote.NewPlayingMoviesRepo
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class NowPlayingMoviesRepoImpl(private val api: Api) : NewPlayingMoviesRepo {
    override suspend fun getNewPlayingMovies(apiKey: String): Flow<DataState<BaseResponse<List<NowPlayingMoviesDto>>>> =
        safeApiCall { api.getNewPlayingMovies(apiKey) }
}
