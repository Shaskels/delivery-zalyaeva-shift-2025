package com.example.delivery_zalyaeva_shift_2025.ui.main

import com.example.delivery_zalyaeva_shift_2025.ui.screens.CalculateDeliveryRoute
import kotlin.reflect.KClass

enum class NavigationOptions(val route: KClass<*>) {
    CALCULATE_DELIVERY(CalculateDeliveryRoute::class)
}