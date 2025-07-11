package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.datasource

import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.entity.DeliveryPointModel
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.entity.PackageTypeModel

interface RemoteDataSource {
    suspend fun getDeliveryPoints(): List<DeliveryPointModel>

    suspend fun getPackageTypes(): List<PackageTypeModel>
}