package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.ui

import android.os.Parcelable
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryPoint
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class DeliveryPoints(
    val deliveryPointsType: DeliveryPointType,
    val deliveryPoints: List<DeliveryPoint>
) : Parcelable