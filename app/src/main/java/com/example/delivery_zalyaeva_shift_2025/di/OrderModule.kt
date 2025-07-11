package com.example.delivery_zalyaeva_shift_2025.di

import com.example.delivery_zalyaeva_shift_2025.shared.calculation.presentation.OrderViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val orderModule = module {
    viewModelOf(::OrderViewModel)
}