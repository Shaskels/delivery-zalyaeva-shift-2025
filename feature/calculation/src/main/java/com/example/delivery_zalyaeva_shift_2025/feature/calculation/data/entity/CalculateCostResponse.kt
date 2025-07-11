package com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.entity

data class CalculateCostResponse(
    val success: Boolean,
    val reason: String,
    val options: List<CalculationModel>
)
