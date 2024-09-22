package com.example.domain.repo.repoRemote

import com.example.domain.entity.dto.movieDetails.viedos.MovieViediosResponse

interface MovieVideosRepo {
    suspend fun getMovieVideos(movieId: Int, apiKey: String): MovieViediosResponse
}