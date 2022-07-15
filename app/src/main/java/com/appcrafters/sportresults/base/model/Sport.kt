package com.appcrafters.sportresults.base.model

import com.google.gson.annotations.SerializedName

data class Sport(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)