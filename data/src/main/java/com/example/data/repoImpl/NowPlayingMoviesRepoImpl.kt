package com.example.data.repoImpl

import com.example.data.remote.Api
import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.newPlaying.NowPlayingMovieResponse
import com.example.domain.entity.safeApiCall
import com.example.domain.repo.repoRemote.NewPlayingMoviesRepo
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class NowPlayingMoviesRepoImpl(private val api: Api) : NewPlayingMoviesRepo {
    override suspend fun getNewPlayingMovies(apiKey: String): Flow<DataState<BaseResponse<List<NowPlayingMovieResponse>>>> =
        safeApiCall {
            api.getNewPlayingMovies(apiKey)
    }
}
