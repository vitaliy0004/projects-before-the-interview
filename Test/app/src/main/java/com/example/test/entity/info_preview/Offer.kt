package com.example.test.entity.info_preview

import com.example.test.data.dto.info_preview.PriceDto

interface Offer {
    val id: Int
    val price: PriceDto
    val title: String
    val town: String
}