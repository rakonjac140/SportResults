package com.appcrafters.sportresults.eventlist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appcrafters.sportresults.R
import com.appcrafters.sportresults.base.model.Event
import com.appcrafters.sportresults.base.model.Sport
import com.appcrafters.sportresults.sportlist.recycler.SportRVViewHolder


class EventRVAdapter(private val events: List<Event>, private val onItemClicked: (Int) -> Unit) : RecyclerView.Adapter<EventRVViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
       EventRVViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false))

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: EventRVViewHolder, position: Int) {
        holder.bind(events[position], onItemClicked)
    }
}