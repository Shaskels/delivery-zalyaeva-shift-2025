package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.entity

data class PackageTypeModel(
    val id: String,
    val name: String,
    val length: Int,
    val width: Int,
    val weight: Int,
    val height: Int,
)
