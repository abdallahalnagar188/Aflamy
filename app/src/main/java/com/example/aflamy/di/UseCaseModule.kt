package com.example.aflamy.di

import com.example.domain.repo.repoRemote.GenresRepo
import com.example.domain.repo.repoRemote.MovieActorsRepo
import com.example.domain.repo.repoRemote.MovieVideosRepo
import com.example.domain.repo.repoRemote.MoviesByGenresPagesRepo
import com.example.domain.repo.repoRemote.MoviesDetailsRepo
import com.example.domain.repo.repoRemote.MoviesForActorRepo
import com.example.domain.repo.repoRemote.NewPlayingMoviesRepo
import com.example.domain.repo.repoRemote.PopularMoviesInPagesRepo
import com.example.domain.repo.repoRemote.PopularMoviesRepo
import com.example.domain.repo.repoRemote.SearchRepo
import com.example.domain.repo.repoRemote.SimilarMoviesRepo
import com.example.domain.repo.repoRemote.TopRateMoviesInPagesRepo
import com.example.domain.repo.repoRemote.TopRateMoviesRepo
import com.example.domain.repo.repoRemote.UpComingMoviesRepo
import com.example.domain.repo.repoRoom.WishlistRepository
import com.example.domain.usecase.remoteUseCase.GetGenres
import com.example.domain.usecase.remoteUseCase.GetMovieActors
import com.example.domain.usecase.remoteUseCase.GetMovieDetails
import com.example.domain.usecase.remoteUseCase.GetMovieVideos
import com.example.domain.usecase.remoteUseCase.GetMoviesByGenresInPages
import com.example.domain.usecase.remoteUseCase.GetMoviesForActors
import com.example.domain.usecase.remoteUseCase.GetNewPlayingMovies
import com.example.domain.usecase.remoteUseCase.GetPopularMovies
import com.example.domain.usecase.remoteUseCase.GetPopularMoviesInPages
import com.example.domain.usecase.remoteUseCase.GetSearchResult
import com.example.domain.usecase.remoteUseCase.GetSimilarMovies
import com.example.domain.usecase.remoteUseCase.GetTopRateMovies
import com.example.domain.usecase.remoteUseCase.GetTopRateMoviesInPages
import com.example.domain.usecase.remoteUseCase.GetUpComingMovies
import com.example.domain.usecase.roomUescase.AddToWishlistUseCase
import com.example.domain.usecase.roomUescase.GetWishlistMoviesUseCase
import com.example.domain.usecase.roomUescase.RemoveFromWishlistUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providePopularMoviesUseCase(popularMoviesRepo: PopularMoviesRepo): GetPopularMovies {
        return GetPopularMovies(popularMoviesRepo)
    }

    @Provides
    @Singleton
    fun provideTopRateMoviesUseCase(topRateMoviesRepo: TopRateMoviesRepo): GetTopRateMovies {
        return GetTopRateMovies(topRateMoviesRepo)
    }

    @Provides
    @Singleton
    fun provideNewPlayingMoviesUseCase(newPlayingMoviesRepo: NewPlayingMoviesRepo): GetNewPlayingMovies {
        return GetNewPlayingMovies(newPlayingMoviesRepo)
    }

    @Provides
    @Singleton
    fun provideUpComingMoviesUseCase(upComingMoviesRepo: UpComingMoviesRepo): GetUpComingMovies {
        return GetUpComingMovies(upComingMoviesRepo)
    }

    @Provides
    @Singleton
    fun provideMoviesDetailsUseCase(moviesDetailsRepo: MoviesDetailsRepo): GetMovieDetails {
        return GetMovieDetails(moviesDetailsRepo)
    }

    @Provides
    @Singleton
    fun provideMovieVideosUseCase(movieVideosRepo: MovieVideosRepo): GetMovieVideos {
        return GetMovieVideos(movieVideosRepo)
    }

    @Provides
    @Singleton
    fun provideMovieActorsUseCase(movieActorsRepo: MovieActorsRepo): GetMovieActors {
        return GetMovieActors(movieActorsRepo)
    }

    @Provides
    @Singleton
    fun provideSimilarMoviesUseCase(similarMoviesRepo: SimilarMoviesRepo): GetSimilarMovies {
        return GetSimilarMovies(similarMoviesRepo)
    }

    @Provides
    @Singleton
    fun providePopularMoviesInPagesUseCase(popularMoviesRepo: PopularMoviesInPagesRepo): GetPopularMoviesInPages {
        return GetPopularMoviesInPages(popularMoviesRepo)
    }

    @Provides
    @Singleton
    fun provideTopRateMoviesInPagesUseCase(topRateMoviesRepo: TopRateMoviesInPagesRepo): GetTopRateMoviesInPages {
        return GetTopRateMoviesInPages(topRateMoviesRepo)
    }

    @Provides
    @Singleton
    fun provideMoviesByGenresInPagesUseCase(moviesByGenresInPages: MoviesByGenresPagesRepo): GetMoviesByGenresInPages {
        return GetMoviesByGenresInPages(moviesByGenresInPages)
    }

    @Provides
    @Singleton
    fun provideSearchMoviesUseCase(searchRepo: SearchRepo): GetSearchResult {
        return GetSearchResult(searchRepo)
    }

    @Provides
    fun provideAddToWishlistUseCase(repository: WishlistRepository): AddToWishlistUseCase {
        return AddToWishlistUseCase(repository)
    }

    @Provides
    fun provideRemoveFromWishlistUseCase(repository: WishlistRepository): RemoveFromWishlistUseCase {
        return RemoveFromWishlistUseCase(repository)
    }

    @Provides
    fun provideGetWishlistMoviesUseCase(repository: WishlistRepository): GetWishlistMoviesUseCase {
        return GetWishlistMoviesUseCase(repository)
    }

    @Provides
    fun provideGetGenresUseCase(repository: GenresRepo): GetGenres {
        return GetGenres(repository)
    }

    @Provides
    fun provideGetMovieActorsUseCase(repository: MoviesForActorRepo): GetMoviesForActors {
        return GetMoviesForActors(repository)
    }
}