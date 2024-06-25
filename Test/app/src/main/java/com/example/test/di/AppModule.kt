package com.example.test.di

import com.example.test.domain.Repository
import com.example.test.presentation.fragment.air_tickets.air_tickets.Factory
import com.example.test.presentation.fragment.air_tickets.all_tickets.FactoryTickets
import com.example.test.presentation.fragment.air_tickets.search.FactorySearch
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideFactory(repository: Repository): Factory {
        return Factory(repository)
    }

    @Provides
    fun provideSearch(repository: Repository): FactorySearch {
        return FactorySearch(repository)
    }

    @Provides
    fun provideAllTickets(repository: Repository): FactoryTickets {
        return FactoryTickets(repository)
    }
}