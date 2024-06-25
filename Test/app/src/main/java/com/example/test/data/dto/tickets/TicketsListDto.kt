package com.example.test.data.dto.tickets

import com.example.test.entity.tickets.TicketsList
import com.google.gson.annotations.SerializedName

data class TicketsListDto(
    @SerializedName("tickets") override val tickets: List<TicketDto>
) : TicketsList