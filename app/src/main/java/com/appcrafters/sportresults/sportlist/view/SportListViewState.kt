package com.appcrafters.sportresults.sportlist.view

import com.appcrafters.sportresults.base.model.Sport

sealed class SportListViewState {
    object Processing : SportListViewState()
    data class DataRecieved(val sports: List<Sport>) : SportListViewState()
    data class ErrorRecieved(val message: String) : SportListViewState()
}