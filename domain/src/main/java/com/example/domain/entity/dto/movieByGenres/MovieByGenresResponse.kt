package com.example.domain.entity.dto.movieByGenres


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieByGenresResponse(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var results: List<MovieByGenresDto>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
):Parcelable