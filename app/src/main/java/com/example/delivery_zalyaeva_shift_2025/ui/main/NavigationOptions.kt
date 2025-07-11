package com.example.delivery_zalyaeva_shift_2025.ui.main

import com.example.delivery_zalyaeva_shift_2025.feature.history.HistoryRoute
import com.example.delivery_zalyaeva_shift_2025.feature.profile.ProfileRoute
import com.example.delivery_zalyaeva_shift_2025.ui.screens.calculateDelivery.CalculateDeliveryRoute
import kotlin.reflect.KClass

enum class NavigationOptions(val route: KClass<*>) {
    CALCULATE_DELIVERY(CalculateDeliveryRoute::class),
    PROFILE(ProfileRoute::class),
    HISTORY(HistoryRoute::class)
}