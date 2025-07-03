package com.example.delivery_zalyaeva_shift_2025.data.remote

import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.CalculateCostRequest
import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.toPackageToSend
import com.example.delivery_zalyaeva_shift_2025.data.remote.entity.toSenderReceiverPoint
import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.domain.entity.PackageType

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