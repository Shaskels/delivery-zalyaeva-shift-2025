package com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity

data class PackageType (
    val id: String,
    val name: String,
    val length: Int,
    val width: Int,
    val height: Int,
    val weight: Int,
)