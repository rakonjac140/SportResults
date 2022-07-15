package com.appcrafters.sportresults.eventlist.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.appcrafters.sportresults.base.model.Event
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_sport_details.view.*
import kotlinx.android.synthetic.main.item_event.view.*


class EventRVViewHolder(view: View) : RecyclerView.ViewHolder(view){
    fun bind(event: Event, onItemClicked: (Int) -> Unit)
    {
        itemView.eventName.text = event.homeTeam?.name + " - " + event.awayTeam?.name
        itemView.eventTime.text = event.startAt

        itemView.setOnClickListener {
            onItemClicked.invoke(event.id)
        }
    }
}