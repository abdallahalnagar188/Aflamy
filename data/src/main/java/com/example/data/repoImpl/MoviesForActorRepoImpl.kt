package com.example.data.repoImpl

import com.example.data.remote.Api
import com.example.domain.entity.dto.movieDetails.actors.moviesForActors.MoviesForActorResponse
import com.example.domain.repo.repoRemote.MoviesForActorRepo

class MoviesForActorRepoImpl(private val api: Api) : MoviesForActorRepo  {
    override suspend fun getMoviesForActor(apiKey: String, personId: Int):MoviesForActorResponse {
        return api.getMoviesForActor(personId, apiKey)

    }
}