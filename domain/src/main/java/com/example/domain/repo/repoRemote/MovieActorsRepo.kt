package com.example.domain.repo.repoRemote

import com.example.domain.entity.dto.movieDetails.actors.MovieActorsResponse

interface MovieActorsRepo {
    suspend fun  getMovieActors(movieId: Int, apiKey: String): MovieActorsResponse
}