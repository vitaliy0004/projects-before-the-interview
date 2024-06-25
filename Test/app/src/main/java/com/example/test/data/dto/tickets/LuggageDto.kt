package com.example.test.data.dto.tickets

import com.example.test.data.dto.info_preview.PriceDto
import com.example.test.entity.tickets.Luggage
import com.google.gson.annotations.SerializedName

data class LuggageDto(
    @SerializedName("has_luggage") override val hasLuggage: Boolean,
    @SerializedName("price") override val price: PriceDto
) : Luggage