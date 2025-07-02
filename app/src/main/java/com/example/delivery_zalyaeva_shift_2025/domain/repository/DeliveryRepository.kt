package com.example.delivery_zalyaeva_shift_2025.domain.repository

import com.example.delivery_zalyaeva_shift_2025.domain.entity.Calculation
import com.example.delivery_zalyaeva_shift_2025.domain.entity.PackageType
import com.example.delivery_zalyaeva_shift_2025.domain.entity.Points

interface DeliveryRepository {

    suspend fun getDeliveryPoints(): List<Points>

    suspend fun getPackageTypes(): List<PackageType>

    suspend fun getCostCalculation(): Calculation
}