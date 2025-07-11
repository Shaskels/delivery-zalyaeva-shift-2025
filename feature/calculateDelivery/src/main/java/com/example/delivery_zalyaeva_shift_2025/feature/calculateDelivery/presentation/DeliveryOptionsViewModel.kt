package com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DeliveryOptionsViewModel(
    private val getDeliveryPointsUseCase: com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.domain.usecase.GetDeliveryPointsUseCase,
    private val getPackageTypesUseCase: com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.domain.usecase.GetPackageTypesUseCase,
): ViewModel() {

    private val _deliveryOptions = MutableLiveData<DeliveryOptionsState>(DeliveryOptionsState.Loading)
    val deliveryOptions: LiveData<DeliveryOptionsState> = _deliveryOptions

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

//            _orderState.update { currentState ->
//                currentState.copy(
//                    senderDelivery = points.firstOrNull(),
//                    receiverDelivery = points.getOrNull(1),
//                    packageType = packageType.firstOrNull()
//                )
//            }
            _deliveryOptions.value = DeliveryOptionsState.Content(points, packageType)
        }
    }
}