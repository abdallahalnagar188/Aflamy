package com.example.domain.usecase.remoteUseCase

import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.topRate.TopRateMoviesDto
import com.example.domain.repo.repoRemote.TopRateMoviesRepo
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class GetTopRateMovies(private val topRateMovies: TopRateMoviesRepo)  {
    suspend operator fun invoke(apiKey: String): Flow<DataState<BaseResponse<List<TopRateMoviesDto>>>> =
        topRateMovies.getTopRateMovies(apiKey)
}