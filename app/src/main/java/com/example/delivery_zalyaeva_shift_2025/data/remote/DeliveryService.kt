package com.example.delivery_zalyaeva_shift_2025.data.remote

import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.CalculateCostRequest
import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.CalculateCostResponse
import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.DeliveryPointsResponse
import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.PackageTypesResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DeliveryService {

    @GET("points")
    suspend fun getDeliveryPoints(): DeliveryPointsResponse

    @GET("package/types")
    suspend fun getPackageTypes(): PackageTypesResponse

    @POST("calc")
    suspend fun getCostCalculation(@Body calculateCostRequest: CalculateCostRequest): CalculateCostResponse
}