package com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.datasource

import android.util.Log
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.DeliveryService
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.entity.CalculateCostRequest
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.entity.CalculationModel

class DeliveryRemoteDataSource (private val deliveryService: DeliveryService): RemoteDataSource {

    override suspend fun getCostCalculation(calculateCostRequest: CalculateCostRequest): List<CalculationModel> {
        val calculateCostResponse = deliveryService.getCostCalculation(calculateCostRequest)
        if (!calculateCostResponse.success){
            Log.e("DeliveryRemoteDataSource", calculateCostResponse.reason)
        }
        return calculateCostResponse.options
    }
}