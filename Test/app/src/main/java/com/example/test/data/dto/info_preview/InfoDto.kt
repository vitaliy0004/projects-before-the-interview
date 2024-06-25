package com.example.test.data.dto.info_preview

import com.example.test.entity.info_preview.Info
import com.google.gson.annotations.SerializedName

data class InfoDto(
    @SerializedName("offers") override val offers: List<OfferDto>
) : Info