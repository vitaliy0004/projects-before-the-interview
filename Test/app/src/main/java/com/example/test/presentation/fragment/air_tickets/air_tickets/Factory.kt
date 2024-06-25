package com.example.test.presentation.fragment.air_tickets.air_tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test.domain.Repository

class Factory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModuleAirTickets(repository) as T
    }
}