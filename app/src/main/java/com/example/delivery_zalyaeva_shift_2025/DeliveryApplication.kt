package com.example.delivery_zalyaeva_shift_2025

import android.app.Application
import com.example.delivery_zalyaeva_shift_2025.di.networkModule
import com.example.delivery_zalyaeva_shift_2025.di.orderModule
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.di.calculateDeliveryModule
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.di.deliveryCalculationModule
import com.example.delivery_zalyaeva_shift_2025.feature.findPackage.di.findPackageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DeliveryApplication: Application() {

    override fun onCreate() {

        super.onCreate()

        startKoin{
            androidLogger(level = Level.DEBUG)
            androidContext(this@DeliveryApplication)

            modules (
                networkModule,
                deliveryCalculationModule,
                calculateDeliveryModule,
                orderModule,
                findPackageModule
            )
        }
    }
}