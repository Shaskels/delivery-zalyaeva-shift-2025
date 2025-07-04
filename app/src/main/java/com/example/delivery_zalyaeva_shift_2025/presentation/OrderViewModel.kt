package com.example.delivery_zalyaeva_shift_2025.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.domain.entity.Order
import com.example.delivery_zalyaeva_shift_2025.domain.entity.PackageType
import com.example.delivery_zalyaeva_shift_2025.domain.usecase.CalculateCostUseCase
import com.example.delivery_zalyaeva_shift_2025.domain.usecase.GetDeliveryPointsUseCase
import com.example.delivery_zalyaeva_shift_2025.domain.usecase.GetPackageTypesUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class OrderViewModel(
    private val getDeliveryPointsUseCase: GetDeliveryPointsUseCase,
    private val getPackageTypesUseCase: GetPackageTypesUseCase,
    private val calculateCostUseCase: CalculateCostUseCase
) : ViewModel() {

    private val _orderState = MutableStateFlow(Order(null, null, null))
    val orderState: StateFlow<Order> = _orderState.asStateFlow()

    private val _deliveryOptions =
        MutableLiveData<DeliveryOptionsState>(DeliveryOptionsState.Loading)
    val deliveryOptions: LiveData<DeliveryOptionsState> = _deliveryOptions

    init {
        getDeliveryOptions()
    }

    fun getDeliveryOptions() {
        _deliveryOptions.value = DeliveryOptionsState.Loading
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.e("OrderViewModel", exception.message.toString())
            _deliveryOptions.value = DeliveryOptionsState.Error
        }
        viewModelScope.launch(handler) {
            var points: List<DeliveryPoint> = emptyList()
            var packageType: List<PackageType> = emptyList()
            listOf(
                launch { points = getDeliveryPointsUseCase.invoke() },
                launch { packageType = getPackageTypesUseCase.invoke() }
            ).joinAll()
            _deliveryOptions.value = DeliveryOptionsState.Success(points, packageType)
        }
    }
}