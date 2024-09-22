package com.example.domain.usecase.remoteUseCase

import com.example.domain.repo.repoRemote.MovieVideosRepo

class GetMovieVideos(private val movieVideosRepo: MovieVideosRepo){
    suspend operator fun invoke(movieId: Int, apiKey: String) = movieVideosRepo.getMovieVideos(movieId, apiKey)
}