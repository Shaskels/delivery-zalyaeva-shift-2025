package com.example.delivery_zalyaeva_shift_2025.data.remote.entity

import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryType

data class CalculationModel(
    val id: String,
    val price: Long,
    val days: Int,
    val name: String,
    val type: DeliveryType,
)
