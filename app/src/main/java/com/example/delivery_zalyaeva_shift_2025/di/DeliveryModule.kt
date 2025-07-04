package com.example.delivery_zalyaeva_shift_2025.di

import com.example.delivery_zalyaeva_shift_2025.data.remote.DeliveryService
import com.example.delivery_zalyaeva_shift_2025.data.remote.CalculateCostRequestMaker
import com.example.delivery_zalyaeva_shift_2025.data.repository.DeliveryRepositoryImpl
import com.example.delivery_zalyaeva_shift_2025.domain.repository.DeliveryRepository
import com.example.delivery_zalyaeva_shift_2025.data.remote.datasource.DeliveryRemoteDataSource
import com.example.delivery_zalyaeva_shift_2025.data.remote.datasource.RemoteDataSource
import com.example.delivery_zalyaeva_shift_2025.domain.usecase.GetDeliveryPointsUseCase
import com.example.delivery_zalyaeva_shift_2025.domain.usecase.GetPackageTypesUseCase
import com.example.delivery_zalyaeva_shift_2025.domain.usecase.CalculateCostUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val deliveryModule = module {
    single { get<Retrofit>().create(DeliveryService::class.java)}
    factoryOf(::CalculateCostRequestMaker)
    factoryOf(::DeliveryRepositoryImpl) bind DeliveryRepository::class
    factoryOf(::DeliveryRemoteDataSource) bind RemoteDataSource::class

    factoryOf(::GetDeliveryPointsUseCase)
    factoryOf(::GetPackageTypesUseCase)
    factoryOf(::CalculateCostUseCase)
}