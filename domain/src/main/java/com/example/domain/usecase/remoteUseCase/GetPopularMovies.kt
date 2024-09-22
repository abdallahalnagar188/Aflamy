package com.example.domain.usecase.remoteUseCase

import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.popularMovies.PopularResponse
import com.example.domain.repo.repoRemote.PopularMoviesRepo
import com.example.domain.state.DataState
import kotlinx.coroutines.flow.Flow

class GetPopularMovies(private val popularMoviesRepo: PopularMoviesRepo) {
    suspend operator fun invoke(apiKey: String): Flow<DataState<BaseResponse<List<PopularResponse>>>> =
        popularMoviesRepo.getPopularMovies(apiKey)
}
