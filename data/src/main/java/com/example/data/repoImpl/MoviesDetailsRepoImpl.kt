package com.example.data.repoImpl

import com.example.data.remote.Api
import com.example.domain.entity.dto.movieDetails.MovieDetailsResponse
import com.example.domain.repo.repoRemote.MoviesDetailsRepo

class MoviesDetailsRepoImpl(private val api: Api) : MoviesDetailsRepo {
    override suspend fun getMovieDetails(movieId: Int, apiKey: String): MovieDetailsResponse {
        return api.getMovieDetails(movieId, apiKey)
    }
}