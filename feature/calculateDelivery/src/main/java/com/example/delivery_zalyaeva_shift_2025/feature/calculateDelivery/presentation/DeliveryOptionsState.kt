package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.presentation

import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.PackageType

sealed interface DeliveryOptionsState {
    data object Error : DeliveryOptionsState
    data object Loading : DeliveryOptionsState
    data class Content(val points: List<DeliveryPoint>, val packageTypes: List<PackageType>) :
        DeliveryOptionsState
}