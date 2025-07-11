package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.repository

import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.datasource.RemoteDataSource
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.entity.toDomainDeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.entity.toDomainPackageType
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.domain.repository.DeliveryRepository
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.PackageType

class DeliveryRepositoryImpl(private val remoteDataSource: RemoteDataSource): DeliveryRepository {
    override suspend fun getDeliveryPoints(): List<DeliveryPoint> {
        return remoteDataSource.getDeliveryPoints().map { it.toDomainDeliveryPoint() }
    }

    override suspend fun getPackageTypes(): List<PackageType> {
        return remoteDataSource.getPackageTypes().map { it.toDomainPackageType() }
    }
}