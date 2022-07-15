package com.appcrafters.sportresults.eventdetails.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.appcrafters.sportresults.R
import com.appcrafters.sportresults.base.data.ApiServiceProvider
import com.appcrafters.sportresults.base.data.SportDataSource
import com.appcrafters.sportresults.base.functional.ViewModelFactoryUtil
import com.appcrafters.sportresults.base.model.Event
import com.appcrafters.sportresults.eventdetails.viewmodel.EventDetailsViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_event_details.*



class EventDetailsFragment : Fragment() {

    private var eventId: Int = -1
    private lateinit var viewModel: EventDetailsViewModel

    companion object {

        private const val EVENT_ID = "EVENT_ID"

        fun newInstance(eventId: Int): EventDetailsFragment {

            return EventDetailsFragment().apply {
                arguments = Bundle().apply { putInt(EVENT_ID, eventId) }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       eventId = arguments?.getInt(EVENT_ID) ?: -1
        viewModel = ViewModelProvider(this, ViewModelFactoryUtil.viewModelFactory {
            EventDetailsViewModel(SportDataSource(ApiServiceProvider.sportApiService))
        }).get(EventDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->

            //eventDetailsProgressBar.isVisible = state is eventDetailsViewState.Processing
            if (state is EventDetailsViewState.DataReceived) setUpView(state.event.data)
            else if (state is EventDetailsViewState.ErrorReceived) showError(state.message)

        })
        Log.d("Event id",eventId.toString())
        viewModel.getEventById(eventId)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    private fun setUpView(event: Event){
             matchStart.text = event.startAt?.toString()
             matchScore.text = event.homeScore?.current.toString() + " - " + event.awayScore?.current.toString()
             status.text = event.status?.toString()
             homeTeam.text = event.homeTeam?.name
             awayTeam.text = event.awayTeam?.name
        if(event.mainOdds?.outcome1?.value == null)
        {
            win1.text = "N/A"
        }
        else
        {
            win1.text =  event.mainOdds?.outcome1?.value.toString()
        }

        if(event.mainOdds?.outcome2?.value == null)
        {
            win2.text = "N/A"
        }
        else
        {
            win2.text =  event.mainOdds?.outcome2?.value.toString()
        }

        if(event.mainOdds?.outcomeX?.value == null)
        {
            draw.text = "N/A"
        }
        else
        {
            draw.text = event.mainOdds?.outcomeX?.value.toString()
        }

        if( event.league?.name == null)
        {
            tournamentName.text = "N/A"
        }
        else
        {
            tournamentName.text = event.league?.name
        }
             Glide.with(this).load(event.homeTeam?.logo).into(logoImg)
             Glide.with(this).load(event.awayTeam?.logo).into(logoImg2)
    }
}