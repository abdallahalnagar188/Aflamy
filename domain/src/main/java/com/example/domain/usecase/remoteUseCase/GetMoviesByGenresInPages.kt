package com.example.domain.usecase.remoteUseCase

import com.example.domain.repo.repoRemote.MoviesByGenresPagesRepo

class GetMoviesByGenresInPages(private val moviesByGenresRepo: MoviesByGenresPagesRepo) {
    suspend operator fun invoke(apiKey: String, genreId: String) = moviesByGenresRepo.getMoviesByGenres(apiKey, genreId)
}
