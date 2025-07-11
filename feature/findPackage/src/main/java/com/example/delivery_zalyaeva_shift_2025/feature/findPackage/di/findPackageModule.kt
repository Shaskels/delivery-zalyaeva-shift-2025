package com.example.delivery_zalyaeva_shift_2025.feature.findPackage.di

import com.example.delivery_zalyaeva_shift_2025.feature.findPackage.presentation.PackageFindViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val findPackageModule = module {
    viewModelOf(::PackageFindViewModel)
}