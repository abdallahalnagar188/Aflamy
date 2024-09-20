package com.example.domain.entity.models

data class MovieModelForLocal(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val releaseDate: String?,
    val voteAverage: Double?,

)