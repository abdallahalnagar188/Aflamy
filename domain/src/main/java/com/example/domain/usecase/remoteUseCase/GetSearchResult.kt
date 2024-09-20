package com.example.domain.usecase.remoteUseCase

import com.example.domain.repo.repoRemote.SearchRepo

class GetSearchResult (private val searchRepo: SearchRepo) {
    suspend operator fun invoke(query: String, apiKey: String) = searchRepo.searchMovies(query, apiKey)
}