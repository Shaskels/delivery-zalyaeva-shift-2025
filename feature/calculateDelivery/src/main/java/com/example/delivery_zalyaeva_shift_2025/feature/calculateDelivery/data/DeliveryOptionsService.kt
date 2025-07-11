package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data

import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.entity.DeliveryPointsResponse
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.entity.PackageTypesResponse
import retrofit2.http.GET

interface DeliveryOptionsService {
    @GET("points")
    suspend fun getDeliveryPoints(): DeliveryPointsResponse

    @GET("package/types")
    suspend fun getPackageTypes(): PackageTypesResponse
}