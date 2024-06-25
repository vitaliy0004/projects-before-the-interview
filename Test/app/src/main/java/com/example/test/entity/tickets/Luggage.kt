package com.example.test.entity.tickets

import com.example.test.data.dto.info_preview.PriceDto

interface Luggage {
    val hasLuggage: Boolean
    val price: PriceDto
}