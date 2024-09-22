package com.example.domain.usecase.remoteUseCase

import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.upComing.UpComingMoviesResponse
import com.example.domain.repo.repoRemote.UpComingMoviesRepo
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class GetUpComingMovies(private val upComingMovies: UpComingMoviesRepo)  {
    suspend operator fun invoke(apiKey: String): Flow<DataState<BaseResponse<List<UpComingMoviesResponse>>>> =
        upComingMovies.getUpComingMovies(apiKey)
}