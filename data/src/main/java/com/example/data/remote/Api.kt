package com.example.data.remote

import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.movieDetails.MovieDetailsResponse
import com.example.domain.entity.dto.movieDetails.actors.MovieActorsResponse
import com.example.domain.entity.dto.movieDetails.similer.SimilarMovies
import com.example.domain.entity.dto.movieDetails.viedos.MovieViediosResponse
import com.example.domain.entity.dto.newPlaying.NowPlayingMovieResponse
import com.example.domain.entity.dto.popularMovies.PopularResponse
import com.example.domain.entity.dto.search.movies.SearchResponse
import com.example.domain.entity.dto.topRate.TopRateResponse
import com.example.domain.entity.dto.upComing.UpComingMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): BaseResponse<List<PopularResponse>>

    @GET("movie/top_rated")
    suspend fun getTopRateMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): BaseResponse<List<TopRateResponse>>

    @GET("movie/now_playing")
   suspend fun getNewPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 2
    ): BaseResponse<List<NowPlayingMovieResponse>>

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): BaseResponse<List<UpComingMoviesResponse>>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailsResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): MovieViediosResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieActors(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieActorsResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): BaseResponse<List<SimilarMovies>>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): SearchResponse


} 