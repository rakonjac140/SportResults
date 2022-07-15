package com.appcrafters.sportresults.sportlist.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.appcrafters.sportresults.base.model.Sport
import kotlinx.android.synthetic.main.item_sport.view.*


class SportRVViewHolder(view: View) : RecyclerView.ViewHolder(view){
    fun bind(sport: Sport, onItemClicked: (Int) -> Unit)
    {
        itemView.sportIdName.text = sport.name
        itemView.setOnClickListener {
             onItemClicked.invoke(sport.id)
        }
    }
}