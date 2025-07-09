package com.example.delivery_zalyaeva_shift_2025.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.delivery_zalyaeva_shift_2025.domain.usecase.CalculateCostUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class DeliveryCalculationViewModel(
    private val calculateCostUseCase: CalculateCostUseCase
): ViewModel() {

    private val _deliveryCalculations = MutableLiveData<DeliveryCalculationsState>()
    val deliveryCalculations: LiveData<DeliveryCalculationsState> = _deliveryCalculations

    fun getDeliveryCalculations(order: Order) {
        _deliveryCalculations.value = DeliveryCalculationsState.Loading
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("OrderViewModel", exception.message.toString())
            _deliveryCalculations.value = DeliveryCalculationsState.Error
        }
        viewModelScope.launch(handler) {
            val calculations =
                if (order.packageType != null
                    && order.senderDelivery != null
                    && order.receiverDelivery != null
                ) {
                    calculateCostUseCase(
                        order.packageType,
                        order.senderDelivery,
                        order.receiverDelivery,
                    )
                } else emptyList()
            if (calculations.isEmpty())
                _deliveryCalculations.value = DeliveryCalculationsState.Error
            else
                _deliveryCalculations.value = DeliveryCalculationsState.Content(calculations)
        }
    }
}