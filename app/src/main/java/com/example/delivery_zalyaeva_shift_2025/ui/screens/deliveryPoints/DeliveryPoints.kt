package com.example.delivery_zalyaeva_shift_2025.ui.screens.deliveryPoints

import android.os.Parcelable
import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryPoint
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class DeliveryPoints(
    val deliveryPointsType: DeliveryPointType,
    val deliveryPoints: List<DeliveryPoint>
) : Parcelable