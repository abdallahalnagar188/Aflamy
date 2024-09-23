package com.example.aflamy.di

import com.example.data.remote.Api
import com.example.data.repoImpl.MovieActorsRepoImpl
import com.example.data.repoImpl.MovieVideosRepoImpl
import com.example.data.repoImpl.MoviesDetailsRepoImpl
import com.example.data.repoImpl.NowPlayingMoviesRepoImpl
import com.example.data.repoImpl.PopularMoviesInPagesRepoImpl
import com.example.data.repoImpl.PopularMoviesRepoImpl
import com.example.data.repoImpl.SearchRepoImpl
import com.example.data.repoImpl.SimilarRepoImpl
import com.example.data.repoImpl.TopRateMoviesInPagesRepoImpl
import com.example.data.repoImpl.TopRateMoviesRepoImpl
import com.example.data.repoImpl.UpComingMoviesRepoImpl
import com.example.domain.repo.repoRemote.MovieActorsRepo
import com.example.domain.repo.repoRemote.MovieVideosRepo
import com.example.domain.repo.repoRemote.MoviesDetailsRepo
import com.example.domain.repo.repoRemote.NewPlayingMoviesRepo
import com.example.domain.repo.repoRemote.PopularMoviesInPagesRepo
import com.example.domain.repo.repoRemote.PopularMoviesRepo
import com.example.domain.repo.repoRemote.SearchRepo
import com.example.domain.repo.repoRemote.SimilarMoviesRepo
import com.example.domain.repo.repoRemote.TopRateMoviesInPagesRepo
import com.example.domain.repo.repoRemote.TopRateMoviesRepo
import com.example.domain.repo.repoRemote.UpComingMoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun providePopularMoviesRepo(apiService: Api): PopularMoviesRepo {
        return PopularMoviesRepoImpl(apiService)
    }
    @Provides
    @Singleton
    fun provideTopRateMoviesRepo(apiService: Api): TopRateMoviesRepo {
        return TopRateMoviesRepoImpl(apiService)
    }
    @Provides
    @Singleton
    fun provideNewPlayingMoviesRepo(apiService: Api): NewPlayingMoviesRepo {
        return NowPlayingMoviesRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUpComingMoviesRepo(apiService: Api): UpComingMoviesRepo {
        return UpComingMoviesRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideMoviesDetailsRepo(apiService: Api): MoviesDetailsRepo {
        return MoviesDetailsRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideMovieVideosRepo(apiService: Api): MovieVideosRepo {
        return MovieVideosRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideMovieActorsRepo(apiService: Api): MovieActorsRepo {
        return MovieActorsRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideSimilarMoviesRepo(apiService: Api): SimilarMoviesRepo {
        return SimilarRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun providePopularMoviesInPagesRepo(apiService: Api): PopularMoviesInPagesRepo {
        return PopularMoviesInPagesRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideTopRateMoviesInPagesRepo(apiService: Api): TopRateMoviesInPagesRepo {
        return TopRateMoviesInPagesRepoImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideSearchRepo(apiService: Api): SearchRepo {
        return SearchRepoImpl(apiService)
    }


}
