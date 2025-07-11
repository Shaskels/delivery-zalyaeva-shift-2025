package com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.datasource

import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.entity.CalculateCostRequest
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.entity.CalculationModel


interface RemoteDataSource {

    suspend fun getCostCalculation(calculateCostRequest: CalculateCostRequest): List<CalculationModel>
}