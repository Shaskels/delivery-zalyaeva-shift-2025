package com.example.delivery_zalyaeva_shift_2025.data.remote.entity

data class CalculateCostRequest(
    val packageToSend: PackageToSend,
    val senderPoint: SenderReceiverPoint,
    val receiverPoint: SenderReceiverPoint,
)
