package com.example.test.data.dto.tickets

import com.example.test.entity.tickets.Departure
import com.google.gson.annotations.SerializedName

data class DepartureDto(
    @SerializedName("airport") override val airport: String,
    @SerializedName("date") override val date: String,
    @SerializedName("town") override val town: String
) : Departure