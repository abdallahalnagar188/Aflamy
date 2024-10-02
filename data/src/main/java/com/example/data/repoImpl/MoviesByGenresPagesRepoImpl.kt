package com.example.data.repoImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.paging.ByGenresPagingSource
import com.example.data.remote.Api
import com.example.domain.entity.models.MovieModel
import com.example.domain.repo.repoRemote.MoviesByGenresPagesRepo
import kotlinx.coroutines.flow.Flow

class MoviesByGenresPagesRepoImpl(private val api: Api) : MoviesByGenresPagesRepo {


    override suspend fun getMoviesByGenres(
        apiKey: String,
        genreId: String
    ): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ByGenresPagingSource(apiService = api, apiKey =  apiKey, genreId =  genreId) }
        ).flow
    }

}