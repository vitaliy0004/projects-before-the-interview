package com.example.test.entity.flight

import com.example.test.data.dto.flight.TicketsOfferDto

interface FlightFly {
    val ticketsOffers: List<TicketsOfferDto>
}