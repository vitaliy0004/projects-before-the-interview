package com.example.test.entity.tickets

import com.example.test.data.dto.info_preview.PriceDto
import com.example.test.data.dto.tickets.ArrivalDto
import com.example.test.data.dto.tickets.DepartureDto
import com.example.test.data.dto.tickets.HandLuggageDto
import com.example.test.data.dto.tickets.LuggageDto

interface Tickets {
    val arrival: ArrivalDto
    val badge: String
    val company: String
    val departure: DepartureDto
    val handLuggage: HandLuggageDto
    val hasTransfer: Boolean
    val hasVisaTransfer: Boolean
    val id: Int
    val isExchangable: Boolean
    val isReturnable: Boolean
    val luggage: LuggageDto
    val price: PriceDto
    val providerName: String
}