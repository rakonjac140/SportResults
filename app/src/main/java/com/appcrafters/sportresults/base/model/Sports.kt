package com.appcrafters.sportresults.base.model

import com.google.gson.annotations.SerializedName

data class Sports (
    @SerializedName("data") val list: List<Sport>
)