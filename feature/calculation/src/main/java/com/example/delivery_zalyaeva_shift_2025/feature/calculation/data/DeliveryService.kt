package com.example.delivery_zalyaeva_shift_2025.feature.calculation.data

import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.entity.CalculateCostRequest
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.entity.CalculateCostResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface DeliveryService {

    @POST("calc")
    suspend fun getCostCalculation(@Body calculateCostRequest: CalculateCostRequest): CalculateCostResponse
}