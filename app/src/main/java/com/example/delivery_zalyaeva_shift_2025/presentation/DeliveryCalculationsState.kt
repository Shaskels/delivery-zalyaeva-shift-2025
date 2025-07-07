package com.example.delivery_zalyaeva_shift_2025.presentation

import com.example.delivery_zalyaeva_shift_2025.domain.entity.Calculation

sealed interface DeliveryCalculationsState {
    data object Error: DeliveryCalculationsState
    data object Loading: DeliveryCalculationsState
    data class Content(val calculations: List<Calculation>): DeliveryCalculationsState
}