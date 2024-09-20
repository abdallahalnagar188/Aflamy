package com.example.domain.usecase.remoteUseCase

import com.example.domain.repo.repoRemote.NewPlayingMoviesRepo

class GetNewPlayingMovies(private val newPlayingMoviesRepo: NewPlayingMoviesRepo)  {
    suspend operator fun invoke(apiKey: String) = newPlayingMoviesRepo.getNewPlayingMovies(apiKey)
}