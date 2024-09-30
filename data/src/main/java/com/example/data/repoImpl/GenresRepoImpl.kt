package com.example.data.repoImpl

import com.example.data.remote.Api
import com.example.domain.repo.repoRemote.GenresRepo

class GenresRepoImpl(private val api: Api) : GenresRepo {
    override suspend fun getGenres(apiKey: String) = api.getGenres(apiKey)
}