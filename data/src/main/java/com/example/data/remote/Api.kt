package com.example.data.remote

import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.genres.GenresResponse
import com.example.domain.entity.dto.movieByGenres.MovieByGenresResponse
import com.example.domain.entity.dto.movieDetails.MovieDetailsResponse
import com.example.domain.entity.dto.movieDetails.actors.MovieActorsResponse
import com.example.domain.entity.dto.movieDetails.actors.moviesForActors.MoviesForActorResponse
import com.example.domain.entity.dto.movieDetails.similer.SimilarMoviesDto
import com.example.domain.entity.dto.movieDetails.viedos.MovieViediosResponse
import com.example.domain.entity.dto.newPlaying.NowPlayingMoviesDto
import com.example.domain.entity.dto.popularMovies.PopularResponse
import com.example.domain.entity.dto.search.movies.SearchResponse
import com.example.domain.entity.dto.topRate.TopRateMoviesDto
import com.example.domain.entity.dto.upComing.UpComingMoviesDto
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
    ): BaseResponse<List<TopRateMoviesDto>>

    @GET("movie/now_playing")
   suspend fun getNewPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 2
    ): BaseResponse<List<NowPlayingMoviesDto>>

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): BaseResponse<List<UpComingMoviesDto>>

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
    ): BaseResponse<List<SimilarMoviesDto>>

    @GET("person/{person_id}/movie_credits")
    suspend fun getMoviesForActor(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): MoviesForActorResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenres(
        @Query("with_genres") withoutGenres: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): MovieByGenresResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): SearchResponse

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String,
    ): GenresResponse




} 