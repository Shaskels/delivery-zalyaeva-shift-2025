package com.example.delivery_zalyaeva_shift_2025.presentation

import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.domain.entity.PackageType

sealed interface DeliveryOptionsState {
    data object Error : DeliveryOptionsState
    data object Loading : DeliveryOptionsState
    data class Content(val points: List<DeliveryPoint>, val packageTypes: List<PackageType>) :
        DeliveryOptionsState
}