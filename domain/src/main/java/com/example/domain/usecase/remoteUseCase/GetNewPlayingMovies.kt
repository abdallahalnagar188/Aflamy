package com.example.domain.usecase.remoteUseCase

import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.newPlaying.NowPlayingMovieResponse
import com.example.domain.repo.repoRemote.NewPlayingMoviesRepo
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class GetNewPlayingMovies(private val newPlayingMoviesRepo: NewPlayingMoviesRepo)  {
    suspend operator fun invoke(apiKey: String) : Flow<DataState<BaseResponse<List<NowPlayingMovieResponse>>>> = newPlayingMoviesRepo.getNewPlayingMovies(apiKey)
}