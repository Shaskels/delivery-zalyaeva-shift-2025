package com.example.delivery_zalyaeva_shift_2025.data.repository

import com.example.delivery_zalyaeva_shift_2025.data.remote.CalculateCostRequestMaker
import com.example.delivery_zalyaeva_shift_2025.data.remote.datasource.RemoteDataSource
import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.toDomainCalculation
import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.toDomainDeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.toDomainPackageType
import com.example.delivery_zalyaeva_shift_2025.domain.entity.Calculation
import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.domain.entity.PackageType
import com.example.delivery_zalyaeva_shift_2025.domain.repository.DeliveryRepository

class DeliveryRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val calculateCostRequestMaker: CalculateCostRequestMaker
) : DeliveryRepository {
    override suspend fun getDeliveryPoints(): List<DeliveryPoint> {
        return remoteDataSource.getDeliveryPoints().map { it.toDomainDeliveryPoint() }
    }

    override suspend fun getPackageTypes(): List<PackageType> {
        return remoteDataSource.getPackageTypes().map { it.toDomainPackageType() }
    }

    override suspend fun getCostCalculation(
        packageType: PackageType,
        senderPoint: DeliveryPoint,
        receiverPoint: DeliveryPoint
    ): List<Calculation> {
        val calculationCostRequest = calculateCostRequestMaker.makeCalculateCostRequest(
            packageType,
            senderPoint,
            receiverPoint
        )
        return remoteDataSource.getCostCalculation(calculationCostRequest)
            .map { it.toDomainCalculation() }
    }
}