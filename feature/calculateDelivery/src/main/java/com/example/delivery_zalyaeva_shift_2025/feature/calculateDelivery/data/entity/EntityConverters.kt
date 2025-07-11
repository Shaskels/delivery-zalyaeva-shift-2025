package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.entity

import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.PackageType

fun DeliveryPointModel.toDomainDeliveryPoint(): DeliveryPoint =
    DeliveryPoint(id, name, latitude, longitude)

fun PackageTypeModel.toDomainPackageType(): PackageType =
    PackageType(id, name, length, width, height, weight)
