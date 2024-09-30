package com.example.domain.usecase.remoteUseCase

import com.example.domain.repo.repoRemote.GenresRepo

class GetGenres(private val genresRepo: GenresRepo) {
    suspend operator fun invoke(apiKey: String) = genresRepo.getGenres(apiKey)

}