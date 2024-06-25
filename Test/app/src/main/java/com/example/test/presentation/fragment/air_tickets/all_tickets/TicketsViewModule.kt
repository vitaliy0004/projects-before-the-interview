package com.example.test.presentation.fragment.air_tickets.all_tickets

import androidx.lifecycle.ViewModel
import com.example.test.data.dto.tickets.TicketsListDto
import com.example.test.domain.Repository
import javax.inject.Inject

class TicketsViewModule @Inject constructor(private val repository: Repository) : ViewModel() {
    suspend fun ticked(): TicketsListDto {
        return repository.tickets()
    }
}