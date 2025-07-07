package com.example.delivery_zalyaeva_shift_2025.ui.main

import com.example.delivery_zalyaeva_shift_2025.ui.screens.calculateDelivery.CalculateDeliveryRoute
import com.example.delivery_zalyaeva_shift_2025.ui.screens.deliveryPoints.DeliveryPointsRoute
import kotlin.reflect.KClass

enum class NavigationOptions(val route: KClass<*>) {
    CALCULATE_DELIVERY(CalculateDeliveryRoute::class),
    DELIVERY_POINTS(DeliveryPointsRoute::class),
}