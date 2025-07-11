package com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.entity

import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.Calculation
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.PackageType

fun CalculationModel.toDomainCalculation(): Calculation =
    Calculation(id, price, days, name, type)

fun PackageType.toPackageToSend(): PackageToSend =
    PackageToSend(length, width, weight, height)

fun DeliveryPoint.toSenderReceiverPoint(): SenderReceiverPoint =
    SenderReceiverPoint(latitude, longitude)