package com.example.delivery_zalyaeva_shift_2025.feature.calculation.presentation

import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.Calculation

sealed interface DeliveryCalculationsState {
    data object Error: DeliveryCalculationsState
    data object Loading: DeliveryCalculationsState
    data class Content(val calculations: List<Calculation>): DeliveryCalculationsState
}