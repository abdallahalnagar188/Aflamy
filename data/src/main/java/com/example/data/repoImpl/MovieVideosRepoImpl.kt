package com.example.data.repoImpl

import com.example.data.remote.Api
import com.example.domain.entity.dto.movieDetails.viedos.MovieViediosResponse
import com.example.domain.repo.repoRemote.MovieVideosRepo

class MovieVideosRepoImpl(private val api: Api) : MovieVideosRepo  {
    override suspend fun getMovieVideos(movieId: Int, apiKey: String): MovieViediosResponse {
        return api.getMovieVideos(movieId, apiKey)
    }
}