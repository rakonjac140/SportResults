package com.appcrafters.sportresults.eventdetails.view

import com.appcrafters.sportresults.base.model.Event
import com.appcrafters.sportresults.base.model.EventData

sealed class EventDetailsViewState {
    object Processing: EventDetailsViewState()
    data class DataReceived(val event: EventData) : EventDetailsViewState()
    data class ErrorReceived(val message: String) : EventDetailsViewState()
}