package com.example.delivery_zalyaeva_shift_2025.feature.calculation.domain.repository

import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.Calculation
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.PackageType
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryPoint

interface DeliveryRepository {

    suspend fun getCostCalculation(
        packageType: PackageType,
        senderPoint: DeliveryPoint,
        receiverPoint: DeliveryPoint
    ): List<Calculation>
}