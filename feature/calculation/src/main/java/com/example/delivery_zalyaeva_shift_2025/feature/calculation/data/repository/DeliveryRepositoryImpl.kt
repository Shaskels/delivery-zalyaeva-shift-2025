package com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.repository

import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.CalculateCostRequestMaker
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.entity.toDomainCalculation
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.domain.repository.DeliveryRepository
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.Calculation
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.PackageType

class DeliveryRepositoryImpl(
    private val remoteDataSource: com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.datasource.RemoteDataSource,
    private val calculateCostRequestMaker: CalculateCostRequestMaker
) : DeliveryRepository {

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