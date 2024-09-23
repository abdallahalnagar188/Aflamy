package com.example.data.repoImpl

import com.example.data.remote.Api
import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.movieDetails.similer.SimilarMovies
import com.example.domain.entity.dto.upComing.UpComingMoviesResponse
import com.example.domain.entity.dto.upComing.UpComingResponse
import com.example.domain.entity.safeApiCall
import com.example.domain.repo.repoRemote.SimilarMoviesRepo
import com.example.domain.repo.repoRemote.UpComingMoviesRepo
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class SimilarRepoImpl(private val api: Api) : SimilarMoviesRepo {
    override suspend fun getSimilarMovies(
        movieId: Int,
        apiKey: String
    ): Flow<DataState<BaseResponse<List<SimilarMovies>>>> {
        return safeApiCall {
            api.getSimilarMovies(movieId, apiKey)
        }
    }
}
