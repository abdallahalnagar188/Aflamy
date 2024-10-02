package com.example.domain.entity.dto.movieDetails.actors.moviesForActors


import com.google.gson.annotations.SerializedName

data class MoviesForActorResponse(
    @SerializedName("cast")
    var cast: List<Cast>?,
    @SerializedName("crew")
    var crew: List<Crew>?,
    @SerializedName("id")
    var id: Int?
)