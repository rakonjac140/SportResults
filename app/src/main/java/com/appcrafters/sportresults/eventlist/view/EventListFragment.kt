package com.appcrafters.sportresults.eventlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.appcrafters.sportresults.R
import com.appcrafters.sportresults.base.ICordinator
import com.appcrafters.sportresults.base.data.ApiServiceProvider
import com.appcrafters.sportresults.base.data.SportDataSource
import com.appcrafters.sportresults.base.functional.ViewModelFactoryUtil
import com.appcrafters.sportresults.base.model.Event
import com.appcrafters.sportresults.eventlist.recycler.EventRVAdapter
import com.appcrafters.sportresults.eventlist.viewmodel.EventListViewModel
import kotlinx.android.synthetic.main.fragment_event_list.*

class EventListFragment : Fragment() {

    var sportId: Int = -1
    private lateinit var viewModel: EventListViewModel

    companion object{

        private const val SPORT_ID_KEY = "SPORT_ID"

        fun newInstance(sportId: Int): EventListFragment
        {
            return EventListFragment().apply {
                arguments = Bundle().apply { putInt(SPORT_ID_KEY, sportId) }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sportId = arguments?.getInt(SPORT_ID_KEY) ?: -1
        viewModel = ViewModelProvider(this, ViewModelFactoryUtil.viewModelFactory {
            EventListViewModel(SportDataSource(ApiServiceProvider.sportApiService))
        }).get(EventListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindFromViewModel()
        viewModel.getEventsBySportId(sportId)
    }

    private fun bindFromViewModel(){
        viewModel.state.observe(viewLifecycleOwner) { state ->

            //eventsListProgressBar.isVisible = state is EventsListViewState.Processing

            if (state is  EventListViewState.DataRecieved) {setUpRecyclerView(state.events)}
            else if (state is  EventListViewState.ErrorRecieved) showError(state.message)
        }
    }

    private fun setUpRecyclerView(events: List<Event>) {
        eventsListRV.adapter = EventRVAdapter(events) { id ->
            (activity as ICordinator).showEventDetailsFragment(id)
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }


}