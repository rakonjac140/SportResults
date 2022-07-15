package com.appcrafters.sportresults.base.model


import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("has_logo")
    val hasLogo: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("section_id")
    val sectionId: Int,
    @SerializedName("sport_id")
    val sportId: Int
)