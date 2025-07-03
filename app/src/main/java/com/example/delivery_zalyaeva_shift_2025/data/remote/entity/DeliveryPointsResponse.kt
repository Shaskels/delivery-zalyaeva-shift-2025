package com.example.delivery_zalyaeva_shift_2025.data.remote.entity

data class DeliveryPointsResponse(
    val success: String,
    val reason: String,
    val points: List<DeliveryPointModel>,
)
