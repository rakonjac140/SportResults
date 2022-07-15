package com.appcrafters.sportresults.base.model

import com.google.gson.annotations.SerializedName

data class Events (
    @SerializedName("data") val lista: List<Event>
)