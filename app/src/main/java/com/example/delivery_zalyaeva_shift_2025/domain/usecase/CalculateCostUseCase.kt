package com.example.delivery_zalyaeva_shift_2025.domain.usecase

import com.example.delivery_zalyaeva_shift_2025.domain.entity.Calculation
import com.example.delivery_zalyaeva_shift_2025.domain.repository.DeliveryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CalculateCostUseCase(private val deliveryRepository: DeliveryRepository) {
    suspend operator fun invoke(): Calculation {
        return withContext(Dispatchers.IO) {
            deliveryRepository.getCostCalculation()
        }
    }
}