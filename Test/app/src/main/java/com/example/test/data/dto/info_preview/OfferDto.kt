package com.example.test.data.dto.info_preview

import com.example.test.entity.info_preview.Offer
import com.google.gson.annotations.SerializedName

data class OfferDto(
    @SerializedName("id") override val id: Int,
    @SerializedName("price") override val price: PriceDto,
    @SerializedName("title") override val title: String,
    @SerializedName("town") override val town: String
) : Offer