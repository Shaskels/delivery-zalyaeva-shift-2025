package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.entity

data class DeliveryPointsResponse(
    val success: String,
    val reason: String,
    val points: List<com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.entity.DeliveryPointModel>,
)
