package com.example.delivery_zalyaeva_shift_2025.data.remote.entity

import com.example.delivery_zalyaeva_shift_2025.domain.entity.Calculation
import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.domain.entity.PackageType

fun DeliveryPointModel.toDomainDeliveryPoint(): DeliveryPoint =
    DeliveryPoint(id, name, latitude, longitude)

fun PackageTypeModel.toDomainPackageType(): PackageType =
    PackageType(id, name, length, width, height, weight)

fun CalculationModel.toDomainCalculation(): Calculation =
    Calculation(id, price, days, name, type)

fun PackageType.toPackageToSend(): PackageToSend =
    PackageToSend(length, width, weight, height)

fun DeliveryPoint.toSenderReceiverPoint(): SenderReceiverPoint =
    SenderReceiverPoint(latitude, longitude)