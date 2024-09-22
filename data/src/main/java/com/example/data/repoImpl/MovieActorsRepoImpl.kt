package com.example.data.repoImpl

import com.example.data.remote.Api
import com.example.domain.entity.dto.movieDetails.actors.MovieActorsResponse
import com.example.domain.repo.repoRemote.MovieActorsRepo

class MovieActorsRepoImpl(private val api: Api) : MovieActorsRepo {
    override suspend fun getMovieActors(movieId: Int, apiKey: String): MovieActorsResponse {
        return api.getMovieActors(movieId, apiKey)
    }
}