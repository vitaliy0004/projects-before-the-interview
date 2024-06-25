package com.example.test.presentation.fragment.air_tickets.all_tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test.domain.Repository

class FactoryTickets(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TicketsViewModule(repository) as T
    }
}