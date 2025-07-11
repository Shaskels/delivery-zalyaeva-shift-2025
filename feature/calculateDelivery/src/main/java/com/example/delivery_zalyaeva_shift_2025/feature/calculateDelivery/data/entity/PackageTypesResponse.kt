package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.entity

data class PackageTypesResponse(
    val success: Boolean,
    val reason: String,
    val packages: List<com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.entity.PackageTypeModel>
)
