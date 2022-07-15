package com.appcrafters.sportresults.eventdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcrafters.sportresults.base.data.SportDataSource
import com.appcrafters.sportresults.base.functional.Either
import com.appcrafters.sportresults.eventdetails.view.EventDetailsViewState
import kotlinx.coroutines.launch

class EventDetailsViewModel(private val dataSource: SportDataSource) : ViewModel() {
    private val _state = MutableLiveData<EventDetailsViewState>()
    val state: LiveData<EventDetailsViewState>
        get() = _state

    fun getEventById(id: Int){
        viewModelScope.launch {
            _state.postValue(EventDetailsViewState.Processing)
            _state.postValue(
                when(val result = dataSource.getEventById(id)){
                  is Either.Success -> EventDetailsViewState.DataReceived(result.data)
                  is Either.Error -> EventDetailsViewState.ErrorReceived(result.exception.toString())
                }
            )
        }
    }
}