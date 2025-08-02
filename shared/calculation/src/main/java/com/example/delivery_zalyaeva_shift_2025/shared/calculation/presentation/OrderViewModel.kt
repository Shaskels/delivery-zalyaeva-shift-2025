package com.example.delivery_zalyaeva_shift_2025.shared.calculation.presentation

import androidx.lifecycle.ViewModel
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryType
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.PackageType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel : ViewModel() {

    private var _isInitial = true
    val isInitial = _isInitial
    private val _orderState = MutableStateFlow(
        Order(null, null, null, null, null)
    )
    val orderState = _orderState.asStateFlow()

    fun setSenderDeliveryPoint(deliveryPoint: DeliveryPoint?) {
        _isInitial = false
        _orderState.update { currentState ->
            currentState.copy(
                senderDelivery = deliveryPoint
            )
        }
    }

    fun setReceiverDeliveryPoint(deliveryPoint: DeliveryPoint?) {
        _isInitial = false
        _orderState.update { currentState ->
            currentState.copy(
                receiverDelivery = deliveryPoint
            )
        }
    }

    fun setPackageType(packageType: PackageType?) {
        _isInitial = false
        _orderState.update { currentState ->
            currentState.copy(
                packageType = packageType
            )
        }
    }

    fun setDeliveryType(deliveryType: DeliveryType?) {
        _isInitial = false
        _orderState.update { currentState ->
            currentState.copy(
                deliveryType = deliveryType
            )
        }
    }

    fun setReceiverData(client: Client) {
        _isInitial = false
        _orderState.update { currentState ->
            currentState.copy(
                receiverData = client
            )
        }
    }

}