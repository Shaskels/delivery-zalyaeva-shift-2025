package com.example.delivery_zalyaeva_shift_2025.data.remote.entity

data class PackageTypesResponse(
    val success: Boolean,
    val reason: String,
    val packages: List<PackageTypeModel>
)
