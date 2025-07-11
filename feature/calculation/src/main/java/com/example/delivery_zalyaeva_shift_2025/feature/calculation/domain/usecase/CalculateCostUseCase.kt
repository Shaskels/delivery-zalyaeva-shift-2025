package com.example.delivery_zalyaeva_shift_2025.feature.calculation.domain.usecase

import com.example.delivery_zalyaeva_shift_2025.feature.calculation.domain.repository.DeliveryRepository
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.Calculation
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.PackageType

class CalculateCostUseCase (private val deliveryRepository: DeliveryRepository) {
    suspend operator fun invoke(
        packageType: PackageType,
        senderPoint: DeliveryPoint,
        receiverPoint: DeliveryPoint
    ): List<Calculation> = deliveryRepository.getCostCalculation(packageType, senderPoint, receiverPoint)
}