package com.example.delivery_zalyaeva_shift_2025.data.remote.datasource

import android.util.Log
import com.example.delivery_zalyaeva_shift_2025.data.remote.DeliveryService
import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.CalculateCostRequest
import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.CalculationModel
import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.DeliveryPointModel
import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.PackageTypeModel

class DeliveryRemoteDataSource (private val deliveryService: DeliveryService): RemoteDataSource {
    override suspend fun getDeliveryPoints(): List<DeliveryPointModel> {
        return deliveryService.getDeliveryPoints().points
    }

    override suspend fun getPackageTypes(): List<PackageTypeModel> {
        return deliveryService.getPackageTypes().packages
    }

    override suspend fun getCostCalculation(calculateCostRequest: CalculateCostRequest): List<CalculationModel> {
        val calculateCostResponse = deliveryService.getCostCalculation(calculateCostRequest)
        if (!calculateCostResponse.success){
            Log.e("DeliveryRemoteDataSource", calculateCostResponse.reason)
        }
        return calculateCostResponse.options
    }
}