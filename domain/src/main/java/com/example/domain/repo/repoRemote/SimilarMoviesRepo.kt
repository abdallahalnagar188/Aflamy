package com.example.domain.repo.repoRemote

import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.movieDetails.similer.SimilarMoviesDto
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface SimilarMoviesRepo {
    suspend fun getSimilarMovies(movieId: Int, apiKey: String): Flow<DataState<BaseResponse<List<SimilarMoviesDto>>>>
}