package com.example.domain.usecase.remoteUseCase

import com.example.domain.repo.repoRemote.UpComingMoviesRepo

class GetUpComingMovies(private val upComingMovies: UpComingMoviesRepo)  {
    suspend operator fun invoke(apiKey: String) = upComingMovies.getUpComingMovies(apiKey)
}