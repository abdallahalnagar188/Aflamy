package com.example.domain.usecase.remoteUseCase

import com.example.domain.repo.repoRemote.MovieActorsRepo
import com.example.domain.repo.repoRemote.MovieVideosRepo

class GetMovieActors(private val movieActorsRepo: MovieActorsRepo){
    suspend operator fun invoke(movieId: Int, apiKey: String) = movieActorsRepo.getMovieActors(movieId, apiKey)
}