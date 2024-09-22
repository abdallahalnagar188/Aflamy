package com.example.domain.entity.dto.movieDetails.actors


import com.google.gson.annotations.SerializedName

data class MovieActorsResponse(
    @SerializedName("cast")
    var cast: List<Cast>?,
    @SerializedName("crew")
    var crew: List<Crew>?,
    @SerializedName("id")
    var id: Int?
)