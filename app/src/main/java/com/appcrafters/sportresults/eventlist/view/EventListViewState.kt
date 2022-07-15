package com.appcrafters.sportresults.eventlist.view

import com.appcrafters.sportresults.base.model.Event

sealed class EventListViewState {
    object Processing: EventListViewState()
    data class DataRecieved(val events: List<Event>) : EventListViewState()
    data class ErrorRecieved(val message: String) : EventListViewState()
}