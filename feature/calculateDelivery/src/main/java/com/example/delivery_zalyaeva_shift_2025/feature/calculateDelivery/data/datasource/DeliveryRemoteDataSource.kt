package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.datasource

import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.DeliveryOptionsService

class DeliveryRemoteDataSource(private val deliveryOptionsService: DeliveryOptionsService): RemoteDataSource {
    override suspend fun getDeliveryPoints(): List<com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.entity.DeliveryPointModel> {
        return deliveryOptionsService.getDeliveryPoints().points
    }

    override suspend fun getPackageTypes(): List<com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.entity.PackageTypeModel> {
        return deliveryOptionsService.getPackageTypes().packages
    }
}