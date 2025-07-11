package com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.entity

import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryType

data class CalculationModel(
    val id: String,
    val price: Long,
    val days: Int,
    val name: String,
    val type: DeliveryType,
)
