package com.example.delivery_zalyaeva_shift_2025.data.remote.datasource

import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.CalculateCostRequest
import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.CalculationModel
import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.DeliveryPointModel
import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.PackageTypeModel

interface RemoteDataSource {

    suspend fun getDeliveryPoints(): List<DeliveryPointModel>

    suspend fun getPackageTypes(): List<PackageTypeModel>

    suspend fun getCostCalculation(calculateCostRequest: CalculateCostRequest): List<CalculationModel>
}