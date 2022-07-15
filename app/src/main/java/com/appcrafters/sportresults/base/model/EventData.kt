package com.appcrafters.sportresults.base.model

import com.google.gson.annotations.SerializedName

data class EventData (
    @SerializedName("data") val data: Event
)