package com.example.domain.repo.repoRemote

import androidx.paging.PagingData
import com.example.domain.entity.models.MovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesByGenresPagesRepo {
    suspend fun getMoviesByGenres(apiKey: String, genreId: String): Flow<PagingData<MovieModel>>
}