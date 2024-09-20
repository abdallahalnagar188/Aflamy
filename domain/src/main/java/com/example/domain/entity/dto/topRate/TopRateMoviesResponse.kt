package com.example.domain.entity.dto.topRate


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class TopRateMoviesResponse(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var results: List<Result>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
):Parcelable