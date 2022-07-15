package com.appcrafters.sportresults.base

interface ICordinator {
    fun showEventsFragment(sportId: Int)
    fun showEventDetailsFragment(eventId: Int)
}