package com.example.delivery_zalyaeva_shift_2025.domain.usecase

import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.domain.repository.DeliveryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDeliveryPointsUseCase(private val deliveryRepository: DeliveryRepository) {
    suspend operator fun invoke(): List<DeliveryPoint>{
        return withContext(Dispatchers.IO){
            deliveryRepository.getDeliveryPoints()
        }
    }
}