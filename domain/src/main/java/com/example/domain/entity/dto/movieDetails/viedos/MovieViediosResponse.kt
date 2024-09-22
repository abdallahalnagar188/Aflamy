package com.example.domain.entity.dto.movieDetails.viedos


import com.google.gson.annotations.SerializedName

data class MovieViediosResponse(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("results")
    var results: List<Result>?
)