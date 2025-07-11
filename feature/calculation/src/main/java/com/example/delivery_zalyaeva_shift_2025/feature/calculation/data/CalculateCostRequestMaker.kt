package com.example.delivery_zalyaeva_shift_2025.feature.calculation.data


import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.entity.CalculateCostRequest
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.entity.toPackageToSend
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.entity.toSenderReceiverPoint
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.PackageType

class CalculateCostRequestMaker {

    fun makeCalculateCostRequest(
        packageType: PackageType,
        senderPoint: DeliveryPoint,
        receiverPoint: DeliveryPoint
    ): CalculateCostRequest {
        return CalculateCostRequest(
            packageType.toPackageToSend(),
            senderPoint.toSenderReceiverPoint(),
            receiverPoint.toSenderReceiverPoint()
        )
    }
}