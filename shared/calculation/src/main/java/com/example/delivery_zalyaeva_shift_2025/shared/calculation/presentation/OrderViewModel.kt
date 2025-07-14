package com.example.delivery_zalyaeva_shift_2025.shared.calculation.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryType
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.PackageType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel: ViewModel() {

    private var isInitial = true
    private val _orderState = MutableStateFlow(Order(null, null, null, null))
    val orderState = _orderState.asStateFlow()

    fun initOrder(order: Order){
        if (isInitial) {
            _orderState.value = order
        }
        isInitial = false
    }

    fun setSenderDeliveryPoint(deliveryPoint: DeliveryPoint?) {
        Log.d("order", deliveryPoint.toString())
        _orderState.update { currentState ->
            currentState.copy(
                senderDelivery = deliveryPoint
            )
        }
    }

    fun setReceiverDeliveryPoint(deliveryPoint: DeliveryPoint?) {
        _orderState.update { currentState ->
            currentState.copy(
                receiverDelivery = deliveryPoint
            )
        }
    }

    fun setPackageType(packageType: PackageType?) {
        _orderState.update { currentState ->
            currentState.copy(
                packageType = packageType
            )
        }
    }

    fun setDeliveryType(deliveryType: DeliveryType?) {
        _orderState.update { currentState ->
            currentState.copy(
                deliveryType = deliveryType
            )
        }
    }

}