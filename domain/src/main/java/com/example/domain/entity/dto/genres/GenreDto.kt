package com.example.domain.entity.dto.genres


import com.google.gson.annotations.SerializedName

data class GenreDto(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?
)