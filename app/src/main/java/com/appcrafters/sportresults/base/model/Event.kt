package com.appcrafters.sportresults.base.model


import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("away_score")
    val awayScore: Score?,
    @SerializedName("away_team")
    val awayTeam: Team?,
    @SerializedName("home_score")
    val homeScore: Score?,
    @SerializedName("home_team")
    val homeTeam: Team?,
    @SerializedName("league")
    val league: League?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main_odds")
    val mainOdds: MainOdds?,
    @SerializedName("name")
    val name: String,
    @SerializedName("start_at")
    val startAt: String?,
    @SerializedName("status")
    val status: String?,
)


