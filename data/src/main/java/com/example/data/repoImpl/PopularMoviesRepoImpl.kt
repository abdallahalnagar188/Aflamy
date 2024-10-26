package com.example.data.repoImpl

import com.example.data.remote.Api
import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.popularMovies.PopularMoviesResponse
import com.example.domain.entity.dto.popularMovies.PopularResponse
import com.example.domain.entity.safeApiCall
import com.example.domain.repo.repoRemote.PopularMoviesRepo
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class PopularMoviesRepoImpl(private val api: Api) : PopularMoviesRepo {
    override suspend fun getPopularMovies(apiKey: String): Flow<DataState<BaseResponse<List<PopularResponse>>>> {
        return safeApiCall {
            api.getPopularMovies(apiKey)
        }
    }
}
