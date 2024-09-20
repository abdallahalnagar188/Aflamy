package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.Api
import com.example.domain.entity.models.MovieModel

class TopRatePagingSource(
    private val apiService: Api,
    private val apiKey: String
) : PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getTopRateMovies(apiKey, currentPage)
            val data = response.results?.map { it.toMovieModel() }
            val nextPage = if (response.page!! < (response.totalPages ?: 0)) currentPage + 1 else null

            LoadResult.Page(
                data = data?: emptyList(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
