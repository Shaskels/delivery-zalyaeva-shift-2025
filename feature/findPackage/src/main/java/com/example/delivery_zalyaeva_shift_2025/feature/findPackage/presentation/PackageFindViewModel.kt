package com.example.delivery_zalyaeva_shift_2025.feature.findPackage.presentation

import androidx.lifecycle.ViewModel

class PackageFindViewModel: ViewModel() {

    fun findOrder(orderNumber: String){
        validateOrderNumber(orderNumber)
    }

    private fun validateOrderNumber(orderNumber: String): Boolean{
        return orderNumber.matches("\\d+".toRegex())
    }
}