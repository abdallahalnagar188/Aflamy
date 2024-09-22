package com.example.domain.repo.repoRemote

import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.topRate.TopRateMoviesResponse
import com.example.domain.entity.dto.topRate.TopRateResponse
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface TopRateMoviesRepo {
    suspend fun getTopRateMovies(apiKey: String):  Flow<DataState<BaseResponse<List<TopRateResponse>>>>
}