package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.domain.usecase

import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.PackageType
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.domain.repository.DeliveryRepository

class GetPackageTypesUseCase(private val deliveryRepository: DeliveryRepository) {
    suspend operator fun invoke(): List<PackageType> = deliveryRepository.getPackageTypes()
}