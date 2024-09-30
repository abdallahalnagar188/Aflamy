package com.example.domain.repo.repoRemote

import com.example.domain.entity.BaseResponse
import com.example.domain.entity.dto.genres.GenreDto
import com.example.domain.entity.dto.genres.GenresResponse

interface GenresRepo {
    suspend fun getGenres(apiKey: String):GenresResponse
}