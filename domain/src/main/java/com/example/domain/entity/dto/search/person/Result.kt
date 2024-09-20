package com.example.domain.entity.dto.search.person


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    @SerializedName("adult")
    var adult: Boolean?,
    @SerializedName("gender")
    var gender: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("known_for")
    var knownFor: List<KnownFor?>?,
    @SerializedName("known_for_department")
    var knownForDepartment: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("original_name")
    var originalName: String?,
    @SerializedName("popularity")
    var popularity: Double?,
    @SerializedName("profile_path")
    var profilePath: String?
): Parcelable{

}