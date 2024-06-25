package com.example.test.data

import com.example.test.data.dto.flight.FlightFlyDto
import com.example.test.data.dto.info_preview.InfoDto
import com.example.test.data.dto.tickets.TicketsListDto
import com.example.test.presentation.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject


class Retrofit @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val infoApi: InfoApi = retrofit.create(InfoApi::class.java)
    val flightApi: FlightApi = retrofit.create(FlightApi::class.java)
    val tickets: TicketsApi = retrofit.create(TicketsApi::class.java)
}

interface InfoApi {
    @GET("ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getInfo(): InfoDto
}

interface FlightApi {
    @GET("38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun flight(): FlightFlyDto
}

interface TicketsApi {
    @GET("c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun tickets(): TicketsListDto
}
