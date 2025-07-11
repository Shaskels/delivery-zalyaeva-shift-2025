package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.di

import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.DeliveryOptionsService
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.datasource.DeliveryRemoteDataSource
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.datasource.RemoteDataSource
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.data.repository.DeliveryRepositoryImpl
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.domain.repository.DeliveryRepository
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.domain.usecase.GetDeliveryPointsUseCase
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.domain.usecase.GetPackageTypesUseCase
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.presentation.DeliveryOptionsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val calculateDeliveryModule = module {
    single { get<Retrofit>().create(DeliveryOptionsService::class.java) }
    factoryOf(::DeliveryRepositoryImpl) bind DeliveryRepository::class
    factoryOf(::DeliveryRemoteDataSource) bind RemoteDataSource::class

    factoryOf(::GetDeliveryPointsUseCase)
    factoryOf(::GetPackageTypesUseCase)

    viewModelOf(::DeliveryOptionsViewModel)
}