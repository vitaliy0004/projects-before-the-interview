package com.example.test.data.dto.tickets

import com.example.test.data.dto.info_preview.PriceDto
import com.example.test.entity.tickets.Tickets
import com.google.gson.annotations.SerializedName

data class TicketDto(
    @SerializedName("arrival") override val arrival: ArrivalDto,
    @SerializedName("badge") override val badge: String,
    @SerializedName("company") override val company: String,
    @SerializedName("departure") override val departure: DepartureDto,
    @SerializedName("hand_luggage") override val handLuggage: HandLuggageDto,
    @SerializedName("has_transfer") override val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer") override val hasVisaTransfer: Boolean,
    @SerializedName("id") override val id: Int,
    @SerializedName("is_exchangable") override val isExchangable: Boolean,
    @SerializedName("is_returnable") override val isReturnable: Boolean,
    @SerializedName("luggage") override val luggage: LuggageDto,
    @SerializedName("price") override val price: PriceDto,
    @SerializedName("provider_name") override val providerName: String
) : Tickets