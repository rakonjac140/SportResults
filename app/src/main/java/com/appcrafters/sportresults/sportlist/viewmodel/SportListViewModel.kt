package com.appcrafters.sportresults.sportlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcrafters.sportresults.base.data.ISportsDataSource
import com.appcrafters.sportresults.base.data.SportDataSource
import com.appcrafters.sportresults.base.functional.Either
import com.appcrafters.sportresults.base.model.MockDataProvider
import com.appcrafters.sportresults.base.model.Sport
import com.appcrafters.sportresults.sportlist.view.SportListViewState
import com.appcrafters.sportresults.sportlist.view.SportListViewState.DataRecieved
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SportListViewModel(private val dataSource: ISportsDataSource) : ViewModel() {

    private val _state = MutableLiveData<SportListViewState>()
    val state: LiveData<SportListViewState>
        get() = _state

    fun getSport(){
        viewModelScope.launch(Dispatchers.IO){

            _state.postValue(SportListViewState.Processing)

            _state.postValue(
            when(val result = dataSource.getSport()){
                is Either.Success -> DataRecieved(result.data.list)
                is Either.Error -> SportListViewState.ErrorRecieved(result.exception.toString())
                }
            )
        }
    }
}