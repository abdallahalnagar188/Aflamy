package com.example.domain.entity

import com.example.domain.entity.dto.upComing.Dates
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class BaseResponse<T>(
    @SerializedName("dates")
    val dates: Dates? = null,
    @SerializedName("page")
    val page: Int? =null,
    @SerializedName("results")
    val results: T? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
): Serializable