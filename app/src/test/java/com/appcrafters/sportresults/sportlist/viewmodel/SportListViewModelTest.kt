package com.appcrafters.sportresults.sportlist.viewmodel

import androidx.lifecycle.Observer
import com.appcrafters.sportresults.base.data.ISportsDataSource
import com.appcrafters.sportresults.base.data.SportDataSource
import com.appcrafters.sportresults.base.functional.Either
import com.appcrafters.sportresults.base.model.Sports
import com.appcrafters.sportresults.eventdetails.view.EventDetailsViewState
import com.appcrafters.sportresults.sportlist.view.SportListViewState
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.openMocks

class SportListViewModelTest {

    @Mock
    lateinit var dataSource: ISportsDataSource
    @Mock
    lateinit var stateObserver: Observer<SportListViewState>

    lateinit var viewModel: SportListViewModel

    @Before
    fun setUp(){
        openMocks(this)
        viewModel = SportListViewModel(dataSource)
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `test getSport, has result, state changed to Processing - DataReceived`() = runBlocking{

        val expectedResult = Sports(listOf())

        `when`(dataSource.getSport()).thenReturn(Either.Success(expectedResult))

        viewModel.getSport()

        verify(stateObserver).onChanged(SportListViewState.Processing)
        verify(stateObserver).onChanged(SportListViewState.DataRecieved(expectedResult.list))
    }
}