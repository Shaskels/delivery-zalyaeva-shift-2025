package com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.entity

import com.google.gson.annotations.SerializedName

data class CalculateCostRequest(
    @SerializedName("package")
    val packageToSend: PackageToSend,
    val senderPoint: SenderReceiverPoint,
    val receiverPoint: SenderReceiverPoint,
)
