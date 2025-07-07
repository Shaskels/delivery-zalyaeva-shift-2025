package com.example.delivery_zalyaeva_shift_2025.domain.usecase

import com.example.delivery_zalyaeva_shift_2025.domain.entity.PackageType
import com.example.delivery_zalyaeva_shift_2025.domain.repository.DeliveryRepository

class GetPackageTypesUseCase(private val deliveryRepository: DeliveryRepository) {
    suspend operator fun invoke(): List<PackageType> = deliveryRepository.getPackageTypes()
}