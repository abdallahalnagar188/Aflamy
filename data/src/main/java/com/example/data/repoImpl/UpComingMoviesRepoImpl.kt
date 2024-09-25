package com.example.data.repoImpl

import com.example.data.remote.Api
import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.upComing.UpComingMoviesDto
import com.example.domain.entity.safeApiCall
import com.example.domain.repo.repoRemote.UpComingMoviesRepo
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class UpComingMoviesRepoImpl(private val api: Api) : UpComingMoviesRepo {
    override suspend fun getUpComingMovies(apiKey: String): Flow<DataState<BaseResponse<List<UpComingMoviesDto>>>> {
        return safeApiCall {
            api.getUpComingMovies(apiKey)
        }
    }
}
