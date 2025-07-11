package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery

import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.ui.DeliveryPoints
import kotlinx.serialization.Serializable

@Serializable
data class DeliveryPointsRoute(
    val deliveryPoints: DeliveryPoints
)