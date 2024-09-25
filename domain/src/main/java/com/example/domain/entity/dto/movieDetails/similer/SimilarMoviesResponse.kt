package com.example.domain.entity.dto.movieDetails.similer

import com.google.gson.annotations.SerializedName


data class SimilarMoviesResponse(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var results: List<SimilarMoviesDto?>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
)