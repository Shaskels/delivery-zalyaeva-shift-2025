package com.example.delivery_zalyaeva_shift_2025.presentation

import com.example.delivery_zalyaeva_shift_2025.domain.entity.Calculation
import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryType
import com.example.delivery_zalyaeva_shift_2025.domain.entity.PackageType

data class Order(
    val senderDelivery: DeliveryPoint?,
    val receiverDelivery: DeliveryPoint?,
    val packageType: PackageType?,
    val deliveryType: DeliveryType?,
)
