package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.domain.usecase

import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.domain.repository.DeliveryRepository

class GetDeliveryPointsUseCase(private val deliveryRepository: DeliveryRepository) {
    suspend operator fun invoke(): List<DeliveryPoint> = deliveryRepository.getDeliveryPoints()
}