package com.example.test.presentation.fragment.air_tickets.air_tickets

import androidx.lifecycle.ViewModel
import com.example.test.data.dto.info_preview.InfoDto
import com.example.test.domain.Repository
import javax.inject.Inject

class ViewModuleAirTickets @Inject constructor(private val repository: Repository) : ViewModel() {
    suspend fun info(): InfoDto {
        return repository.info()
    }
    val listCity = listOf(
        "Сочи",
        "Краснодар",
        "Анапа",
        "Стамбул",
        "Мальдивы",
        "Мадрид",
        "Барселона",
        "Кёльн",
        "Стамбул",
        "Индия",
        "Сидней",
        "Рома",
    )
}