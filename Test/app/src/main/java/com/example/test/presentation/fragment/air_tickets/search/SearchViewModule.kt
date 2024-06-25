package com.example.test.presentation.fragment.air_tickets.search

import androidx.lifecycle.ViewModel
import com.example.test.data.dto.flight.FlightFlyDto
import com.example.test.domain.Repository
import javax.inject.Inject

class SearchViewModule @Inject constructor(private val repository: Repository) : ViewModel() {
    suspend fun flight(): FlightFlyDto {
        return repository.flight()
    }
}