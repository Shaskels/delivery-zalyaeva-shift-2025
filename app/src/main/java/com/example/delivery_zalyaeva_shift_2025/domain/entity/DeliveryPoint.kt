package com.example.delivery_zalyaeva_shift_2025.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class DeliveryPoint(
    val id: String,
    val name: String,
    val latitude: Float,
    val longitude: Float
): Parcelable
