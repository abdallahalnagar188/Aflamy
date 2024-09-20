package com.example.data.repoImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.paging.SearchPagingSource
import com.example.data.remote.Api
import com.example.domain.entity.models.MovieModel
import com.example.domain.repo.repoRemote.SearchRepo
import kotlinx.coroutines.flow.Flow

class SearchRepoImpl(private val apiService: Api) : SearchRepo {
    override suspend fun searchMovies(
        query: String,
        apiKey: String
    ): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchPagingSource(
                    apiService = apiService,
                    query = query,
                    apiKey = apiKey
                )
            }
        ).flow
    }

}