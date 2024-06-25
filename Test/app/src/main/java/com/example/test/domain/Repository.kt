package com.example.test.domain

import com.example.test.data.GetRepository
import com.example.test.data.dto.flight.FlightFlyDto
import com.example.test.data.dto.info_preview.InfoDto
import com.example.test.data.dto.tickets.TicketsListDto
import javax.inject.Inject

class Repository @Inject constructor(private val getRepository: GetRepository) {
    suspend fun info(): InfoDto {
        return getRepository.getInfo()
    }

    suspend fun flight(): FlightFlyDto {
        return getRepository.getFlight()
    }

    suspend fun tickets(): TicketsListDto {
        return getRepository.getTickets()
    }
}