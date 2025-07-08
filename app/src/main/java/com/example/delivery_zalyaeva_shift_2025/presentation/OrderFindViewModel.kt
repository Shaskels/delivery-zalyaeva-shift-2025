package com.example.delivery_zalyaeva_shift_2025.presentation

import androidx.lifecycle.ViewModel

class OrderFindViewModel(): ViewModel() {

    fun findOrder(orderNumber: String){
        validateOrderNumber(orderNumber)
    }

    private fun validateOrderNumber(orderNumber: String): Boolean{
        return orderNumber.matches("\\d+".toRegex())
    }
}