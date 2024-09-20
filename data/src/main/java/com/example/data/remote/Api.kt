package com.example.data.remote

import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.movieDetails.MovieDetailsResponse
import com.example.domain.entity.dto.newPlaying.NewPlayingResponse
import com.example.domain.entity.dto.newPlaying.NowPlayingMovieResponse
import com.example.domain.entity.dto.popularMovies.PopularMoviesResponse
import com.example.domain.entity.dto.search.movies.SearchResponse
import com.example.domain.entity.dto.topRate.TopRateMoviesResponse
import com.example.domain.entity.dto.upComing.UpComingResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): PopularMoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRateMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): TopRateMoviesResponse

    @GET("movie/now_playing")
   suspend fun getNewPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 2
    ): BaseResponse<List<NowPlayingMovieResponse>>

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): UpComingResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailsResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): SearchResponse


} 