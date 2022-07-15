package com.appcrafters.sportresults.sportlist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appcrafters.sportresults.R
import com.appcrafters.sportresults.base.model.Sport

class SportRVAdapter(private val sports: List<Sport>, private val onItemClicked: (Int) -> Unit) : RecyclerView.Adapter<SportRVViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    SportRVViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sport, parent, false))

    override fun getItemCount() = sports.size

    override fun onBindViewHolder(holder: SportRVViewHolder, position: Int) {
    holder.bind(sports[position], onItemClicked)
    }
}