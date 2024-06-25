package com.example.test.data.dto.flight

import com.example.test.data.dto.info_preview.PriceDto
import com.example.test.entity.flight.TicketsOffer
import com.google.gson.annotations.SerializedName

data class TicketsOfferDto(
    @SerializedName("id") override val id: Int,
    @SerializedName("price") override val price: PriceDto,
    @SerializedName("time_range") override val timeRange: List<String>,
    @SerializedName("title") override val title: String
) : TicketsOffer