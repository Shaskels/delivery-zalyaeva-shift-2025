package com.example.delivery_zalyaeva_shift_2025.domain.entity

data class Order(
    val senderDelivery: DeliveryPoint?,
    val receiverDelivery: DeliveryPoint?,
    val calculation: Calculation?,
)
