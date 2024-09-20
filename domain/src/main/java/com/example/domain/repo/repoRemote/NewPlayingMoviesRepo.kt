package com.example.domain.repo.repoRemote

import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.newPlaying.NewPlayingResponse
import com.example.domain.entity.dto.newPlaying.NowPlayingMovieResponse
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface NewPlayingMoviesRepo {
    //todo complete function
    suspend fun getNewPlayingMovies(apiKey: String): Flow<DataState<BaseResponse<List<NowPlayingMovieResponse>>>>
}