package com.appcrafters.sportresults.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.appcrafters.sportresults.R
import com.appcrafters.sportresults.eventdetails.view.EventDetailsFragment
import com.appcrafters.sportresults.eventlist.view.EventListFragment
import com.appcrafters.sportresults.sportlist.view.SportListFragment

class MainActivity : AppCompatActivity(), ICordinator {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment(SportListFragment(),true)
    }

    private fun showFragment(fragment: Fragment, addAsRoot: Boolean = false)
    {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentCointainer, fragment)
        if(!addAsRoot) transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showEventsFragment(sportId: Int) {
        showFragment(EventListFragment.newInstance(sportId))
    }

    override fun showEventDetailsFragment(eventId: Int) {
        showFragment(EventDetailsFragment.newInstance(eventId))
    }

}