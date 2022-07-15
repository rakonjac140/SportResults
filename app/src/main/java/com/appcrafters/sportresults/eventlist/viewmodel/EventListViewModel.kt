package com.appcrafters.sportresults.eventlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcrafters.sportresults.base.data.SportDataSource
import com.appcrafters.sportresults.base.functional.Either
import com.appcrafters.sportresults.eventlist.view.EventListViewState
import kotlinx.coroutines.launch

class EventListViewModel(private val dataSource: SportDataSource) : ViewModel() {
    private val _state = MutableLiveData<EventListViewState>()
    val state: LiveData<EventListViewState>
        get() = _state

    fun getEventsBySportId(id: Int){
        viewModelScope.launch {
            _state.postValue(EventListViewState.Processing)
            _state.postValue(
                when(val result = dataSource.getEventsBySportId(id)){
                    is Either.Success -> EventListViewState.DataRecieved(result.data.lista)
                    is Either.Error -> EventListViewState.ErrorRecieved(result.exception.toString())
                }
            )
        }
    }
}