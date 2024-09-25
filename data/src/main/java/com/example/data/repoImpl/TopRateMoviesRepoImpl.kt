package com.example.data.repoImpl

import com.example.data.remote.Api
import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.topRate.TopRateMoviesDto
import com.example.domain.entity.safeApiCall
import com.example.domain.repo.repoRemote.TopRateMoviesRepo
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class TopRateMoviesRepoImpl(private val api: Api) : TopRateMoviesRepo {
    override suspend fun getTopRateMovies(apiKey: String): Flow<DataState<BaseResponse<List<TopRateMoviesDto>>>> {
        return safeApiCall {
            api.getTopRateMovies(apiKey)
        }
    }
}
