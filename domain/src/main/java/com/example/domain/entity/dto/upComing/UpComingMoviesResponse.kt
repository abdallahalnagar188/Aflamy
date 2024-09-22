package com.example.domain.entity.dto.upComing


import android.os.Parcelable
import com.example.domain.entity.models.MovieModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UpComingMoviesResponse(
    @SerializedName("adult")
    var adult: Boolean?,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("genre_ids")
    var genreIds: List<Int?>?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("original_language")
    var originalLanguage: String?,
    @SerializedName("original_title")
    var originalTitle: String?,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("popularity")
    var popularity: Double?,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("release_date")
    var releaseDate: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("video")
    var video: Boolean?,
    @SerializedName("vote_average")
    var voteAverage: Double?,
    @SerializedName("vote_count")
    var voteCount: Int?
) : Parcelable {

    fun toMovieModel(): MovieModel {
        return MovieModel(
            adult = this.adult ?: false,
            backdropPath = this.backdropPath ?: "",
            genreIds = this.genreIds ?: emptyList(),
            id = this.id ?: 0,
            originalLanguage = this.originalLanguage ?: "",
            originalTitle = this.originalTitle ?: "",
            overview = this.overview ?: "",
            popularity = this.popularity ?: 0.0,
            posterPath = this.posterPath ?: "",
            releaseDate = this.releaseDate ?: "",
            title = this.title ?: "",
            video = this.video ?: false,
            voteAverage = this.voteAverage ?: 0.0,
            voteCount = this.voteCount ?: 0
        )
    }
}