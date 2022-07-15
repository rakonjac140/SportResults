package com.appcrafters.sportresults.sportlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.appcrafters.sportresults.R
import com.appcrafters.sportresults.base.ICordinator
import com.appcrafters.sportresults.base.data.*
import com.appcrafters.sportresults.base.functional.ViewModelFactoryUtil
import com.appcrafters.sportresults.base.model.Sport
import com.appcrafters.sportresults.sportlist.recycler.SportRVAdapter
import com.appcrafters.sportresults.sportlist.viewmodel.SportListViewModel
import kotlinx.android.synthetic.main.fragment_sport_list.*

class SportListFragment : Fragment() {

    lateinit var viewModel: SportListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactoryUtil.viewModelFactory {
            SportListViewModel(SportDataSource(ApiServiceProvider.sportApiService))
        }).get(SportListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sport_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindFromViewModel()
        viewModel.getSport()
    }

    private fun setUpRecyclerView(sports: List<Sport>) {
        sportsListRV.adapter = SportRVAdapter(sports) { id ->
            (activity as ICordinator).showEventsFragment(id)
        }
    }

    private fun bindFromViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->

        sportsListProgressBar.isVisible = state is SportListViewState.Processing

            if (state is SportListViewState.DataRecieved) {setUpRecyclerView(state.sports)}
            else if (state is SportListViewState.ErrorRecieved) showError(state.message)
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}
