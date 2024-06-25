package com.example.test.data.dto.info_preview

import com.example.test.entity.info_preview.Price
import com.google.gson.annotations.SerializedName

data class PriceDto(
    @SerializedName("value") override val value: Int
) : Price