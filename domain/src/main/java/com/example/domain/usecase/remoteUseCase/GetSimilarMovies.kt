package com.example.domain.usecase.remoteUseCase

import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.movieDetails.similer.SimilarMoviesDto
import com.example.domain.repo.repoRemote.SimilarMoviesRepo
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class GetSimilarMovies(private val similarMoviesRepo: SimilarMoviesRepo)  {
    suspend operator fun invoke(movieId: Int,apiKey: String): Flow<DataState<BaseResponse<List<SimilarMoviesDto>>>> =
        similarMoviesRepo.getSimilarMovies(movieId,apiKey)
}