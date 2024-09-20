package com.example.data.repoImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.paging.TopRatePagingSource
import com.example.data.remote.Api
import com.example.domain.entity.models.MovieModel
import com.example.domain.repo.repoRemote.TopRateMoviesInPagesRepo
import kotlinx.coroutines.flow.Flow

class TopRateMoviesInPagesRepoImpl(private val api: Api) : TopRateMoviesInPagesRepo {

    override suspend fun getTopRateMoviesInPages(apiKey: String): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TopRatePagingSource(api, apiKey) }
            ).flow
    }

}
