package com.appcrafters.sportresults.base.model


import com.google.gson.annotations.SerializedName

data class MainOdds(
    @SerializedName("outcome_1")
    val outcome1: Outcome1?,
    @SerializedName("outcome_2")
    val outcome2: Outcome1?,
    @SerializedName("outcome_X")
    val outcomeX: Outcome1?
)