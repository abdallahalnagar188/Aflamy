package com.example.domain.entity.dto.movieDetails.viedos


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    var id: String?,
    @SerializedName("iso_3166_1")
    var iso31661: String?,
    @SerializedName("iso_639_1")
    var iso6391: String?,
    @SerializedName("key")
    var key: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("official")
    var official: Boolean?,
    @SerializedName("published_at")
    var publishedAt: String?,
    @SerializedName("site")
    var site: String?,
    @SerializedName("size")
    var size: Int?,
    @SerializedName("type")
    var type: String?
)