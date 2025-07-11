package com.example.delivery_zalyaeva_shift_2025.shared.calculation.presentation

import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryType
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.PackageType

data class Order(
    val senderDelivery: DeliveryPoint?,
    val receiverDelivery: DeliveryPoint?,
    val packageType: PackageType?,
    val deliveryType: DeliveryType?,
)
