package com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity

data class Calculation(
    val id: String,
    val price: Long,
    val days: Int,
    val name: String,
    val type: DeliveryType,
)