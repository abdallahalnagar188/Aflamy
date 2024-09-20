package com.example.data.repoImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.paging.PopularPagingSource
import com.example.data.remote.Api
import com.example.domain.entity.models.MovieModel
import com.example.domain.repo.repoRemote.PopularMoviesInPagesRepo
import kotlinx.coroutines.flow.Flow

class PopularMoviesInPagesRepoImpl(private val api: Api) : PopularMoviesInPagesRepo {

    override suspend fun getPopularMoviesInPages(apiKey: String): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PopularPagingSource(api, apiKey) }
        ).flow
    }

}
