package com.example.delivery_zalyaeva_shift_2025.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.delivery_zalyaeva_shift_2025.domain.entity.Calculation
import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryType
import com.example.delivery_zalyaeva_shift_2025.domain.entity.PackageType
import com.example.delivery_zalyaeva_shift_2025.domain.usecase.CalculateCostUseCase
import com.example.delivery_zalyaeva_shift_2025.domain.usecase.GetDeliveryPointsUseCase
import com.example.delivery_zalyaeva_shift_2025.domain.usecase.GetPackageTypesUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrderViewModel(
    private val getDeliveryPointsUseCase: GetDeliveryPointsUseCase,
    private val getPackageTypesUseCase: GetPackageTypesUseCase,
    private val calculateCostUseCase: CalculateCostUseCase
) : ViewModel() {

    private val _orderState = MutableStateFlow(Order(null, null, null, null))
    val orderState: StateFlow<Order> = _orderState.asStateFlow()

    private val _deliveryOptions =
        MutableLiveData<DeliveryOptionsState>(DeliveryOptionsState.Loading)
    val deliveryOptions: LiveData<DeliveryOptionsState> = _deliveryOptions

    private val _deliveryCalculations =
        MutableLiveData<DeliveryCalculationsState>(DeliveryCalculationsState.Loading)
    val deliveryCalculations: LiveData<DeliveryCalculationsState> = _deliveryCalculations

    fun getDeliveryOptions() {
        _deliveryOptions.value = DeliveryOptionsState.Loading
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("OrderViewModel", exception.message.toString())
            _deliveryOptions.value = DeliveryOptionsState.Error
        }
        viewModelScope.launch(handler) {
            val pointsDeferred = async { getDeliveryPointsUseCase() }
            val packageTypeDeferred = async { getPackageTypesUseCase() }

            val points = pointsDeferred.await()
            val packageType = packageTypeDeferred.await()
            _orderState.update { currentState ->
                currentState.copy(
                    senderDelivery = points.firstOrNull(),
                    receiverDelivery = points.getOrNull(1),
                    packageType = packageType.firstOrNull(),
                )
            }
            _deliveryOptions.value = DeliveryOptionsState.Content(points, packageType)
        }
    }

    fun getDeliveryCalculations() {
        _deliveryCalculations.value = DeliveryCalculationsState.Loading
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("OrderViewModel", exception.message.toString())
            _deliveryCalculations.value = DeliveryCalculationsState.Error
        }
        viewModelScope.launch(handler) {
            val calculations =
                if (_orderState.value.packageType != null
                    && _orderState.value.senderDelivery != null
                    && _orderState.value.receiverDelivery != null
                ) {
                    calculateCostUseCase(
                        _orderState.value.packageType!!,
                        _orderState.value.senderDelivery!!,
                        _orderState.value.receiverDelivery!!,
                    )
                } else emptyList()
            if (calculations.isEmpty())
                _deliveryCalculations.value = DeliveryCalculationsState.Error
            else
                _deliveryCalculations.value = DeliveryCalculationsState.Content(calculations)
        }
    }

    fun setSenderDeliveryPoint(deliveryPoint: DeliveryPoint) {
        _orderState.update { currentState ->
            currentState.copy(
                senderDelivery = deliveryPoint
            )
        }
    }

    fun setReceiverDeliveryPoint(deliveryPoint: DeliveryPoint) {
        _orderState.update { currentState ->
            currentState.copy(
                receiverDelivery = deliveryPoint
            )
        }
    }

    fun setPackageType(packageType: PackageType) {
        _orderState.update { currentState ->
            currentState.copy(
                packageType = packageType
            )
        }
    }

    fun setDeliveryType(deliveryType: DeliveryType){
        _orderState.update { currentState ->
            currentState.copy(
                deliveryType = deliveryType
            )
        }
    }

}