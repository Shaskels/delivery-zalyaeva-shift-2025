package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.domain.repository

import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.PackageType

interface DeliveryRepository {

    suspend fun getDeliveryPoints(): List<DeliveryPoint>

    suspend fun getPackageTypes(): List<PackageType>

}