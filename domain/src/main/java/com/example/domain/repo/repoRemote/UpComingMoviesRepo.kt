package com.example.domain.repo.repoRemote

import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.upComing.UpComingMoviesDto
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface UpComingMoviesRepo {
    suspend fun getUpComingMovies(apiKey: String): Flow<DataState<BaseResponse<List<UpComingMoviesDto>>>>
}