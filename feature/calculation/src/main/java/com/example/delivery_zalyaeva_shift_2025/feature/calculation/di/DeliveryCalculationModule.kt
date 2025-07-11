package com.example.delivery_zalyaeva_shift_2025.feature.calculation.di

import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.CalculateCostRequestMaker
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.datasource.DeliveryRemoteDataSource
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.datasource.RemoteDataSource
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.repository.DeliveryRepositoryImpl
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.domain.repository.DeliveryRepository
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.domain.usecase.CalculateCostUseCase
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.presentation.DeliveryCalculationViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val deliveryCalculationModule = module {
    single { get<Retrofit>().create(com.example.delivery_zalyaeva_shift_2025.feature.calculation.data.DeliveryService::class.java) }
    factoryOf(::CalculateCostRequestMaker)
    factoryOf(::DeliveryRepositoryImpl) bind DeliveryRepository::class
    factoryOf(::DeliveryRemoteDataSource) bind RemoteDataSource::class

    factoryOf(::CalculateCostUseCase)

    viewModelOf(::DeliveryCalculationViewModel)
}