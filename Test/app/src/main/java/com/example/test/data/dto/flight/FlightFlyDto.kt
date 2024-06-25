package com.example.test.data.dto.flight

import com.example.test.entity.flight.FlightFly
import com.google.gson.annotations.SerializedName

data class FlightFlyDto(
    @SerializedName("tickets_offers") override val ticketsOffers: List<TicketsOfferDto>
) : FlightFly