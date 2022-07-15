package com.appcrafters.sportresults.base.model


import com.google.gson.annotations.SerializedName

data class Score(
    @SerializedName("current")
    val current: Int,
    @SerializedName("display")
    val display: Int
)