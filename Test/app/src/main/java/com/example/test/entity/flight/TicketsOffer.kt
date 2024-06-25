package com.example.test.entity.flight

import com.example.test.data.dto.info_preview.PriceDto

interface TicketsOffer {
    val id: Int
    val price: PriceDto
    val timeRange: List<String>
    val title: String
}