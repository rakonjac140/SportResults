package com.appcrafters.sportresults.base.model

object MockDataProvider {

    fun getSportList(): List<Sport> {

        val sports = mutableListOf<Sport>()

        for (i in 1..5) {
            val sport = Sport(
                i,
                "Sport $i",
                "Sport $i"
            )
            sports.add(sport)
        }
        return sports
    }
}