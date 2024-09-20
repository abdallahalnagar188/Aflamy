package com.example.domain.repo.repoRemote

import com.example.domain.entity.dto.movieDetails.MovieDetailsResponse

interface MoviesDetailsRepo {
    suspend fun getMovieDetails(movieId: Int, apiKey: String): MovieDetailsResponse
}