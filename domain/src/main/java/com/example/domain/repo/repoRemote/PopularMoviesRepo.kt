package com.example.domain.repo.repoRemote

import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.popularMovies.PopularResponse
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepo {
    suspend fun getPopularMovies(apiKey: String): Flow<DataState<BaseResponse<List<PopularResponse>>>>


}