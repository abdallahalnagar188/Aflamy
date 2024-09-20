package com.example.domain.repo.repoRemote

import androidx.paging.PagingData
import com.example.domain.entity.models.MovieModel
import kotlinx.coroutines.flow.Flow

interface TopRateMoviesInPagesRepo {
    suspend fun getTopRateMoviesInPages(apiKey: String): Flow<PagingData<MovieModel>>
}