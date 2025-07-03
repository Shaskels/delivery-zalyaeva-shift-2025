package com.example.delivery_zalyaeva_shift_2025.domain.repository

import com.example.delivery_zalyaeva_shift_2025.domain.entity.Calculation
import com.example.delivery_zalyaeva_shift_2025.domain.entity.PackageType
import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryPoint

interface DeliveryRepository {

    suspend fun getDeliveryPoints(): List<DeliveryPoint>

    suspend fun getPackageTypes(): List<PackageType>

    suspend fun getCostCalculation(
        packageType: PackageType,
        senderPoint: DeliveryPoint,
        receiverPoint: DeliveryPoint
    ): List<Calculation>
}