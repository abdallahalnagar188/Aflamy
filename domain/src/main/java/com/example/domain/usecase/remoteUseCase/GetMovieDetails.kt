package com.example.domain.usecase.remoteUseCase

import com.example.domain.repo.repoRemote.MoviesDetailsRepo

class GetMovieDetails(private val moviesDetailsRepo: MoviesDetailsRepo)  {
    suspend operator fun invoke(movieId: Int, apiKey: String) = moviesDetailsRepo.getMovieDetails(movieId,apiKey)
}