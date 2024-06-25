package com.example.test.data.dto.tickets

import com.example.test.entity.tickets.HandLuggage
import com.google.gson.annotations.SerializedName

data class HandLuggageDto(
    @SerializedName("has_hand_luggage") override val hasHandLuggage: Boolean,
    @SerializedName("size") override val size: String
) : HandLuggage