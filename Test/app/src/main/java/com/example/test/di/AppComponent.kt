package com.example.test.di

import com.example.test.presentation.MainActivity
import com.example.test.presentation.fragment.air_tickets.air_tickets.AirTicketsFragment
import com.example.test.presentation.fragment.air_tickets.all_tickets.AllTicketsFragment
import com.example.test.presentation.fragment.air_tickets.search.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, Dagger::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(fragmentAirTicketsBinding: AirTicketsFragment)
    fun inject(fragmentSearch: SearchFragment)
    fun inject(fragmentAllTickets: AllTicketsFragment)
}