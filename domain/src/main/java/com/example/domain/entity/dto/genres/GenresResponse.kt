package com.example.domain.entity.dto.genres


import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    var genres: List<GenreDto>?
)