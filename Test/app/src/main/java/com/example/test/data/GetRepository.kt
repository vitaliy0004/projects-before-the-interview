package com.example.test.data

import com.example.test.data.dto.flight.FlightFlyDto
import com.example.test.data.dto.info_preview.InfoDto
import com.example.test.data.dto.tickets.TicketsListDto
import javax.inject.Inject

class GetRepository @Inject constructor(private val retrofit: Retrofit) {
    suspend fun getInfo(): InfoDto {
        return retrofit.infoApi.getInfo()
    }

    suspend fun getFlight(): FlightFlyDto {
        return retrofit.flightApi.flight()
    }

    suspend fun getTickets(): TicketsListDto {
        return retrofit.tickets.tickets()
    }
}