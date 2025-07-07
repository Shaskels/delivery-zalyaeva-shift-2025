package com.example.delivery_zalyaeva_shift_2025.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.delivery_zalyaeva_shift_2025.R
import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.domain.entity.PackageType
import com.example.delivery_zalyaeva_shift_2025.presentation.DeliveryOptionsState
import com.example.delivery_zalyaeva_shift_2025.presentation.OrderViewModel
import com.example.delivery_zalyaeva_shift_2025.ui.components.BrandButton
import com.example.delivery_zalyaeva_shift_2025.ui.components.BrandTextField
import com.example.delivery_zalyaeva_shift_2025.ui.components.Picker
import com.example.delivery_zalyaeva_shift_2025.ui.theme.DeliveryTheme

@Composable
fun CalculateDeliveryScreen(
    orderViewModel: OrderViewModel,
    onSelectDeliveryPointClick: (DeliveryPointType, List<DeliveryPoint>) -> Unit
) {
    val deliveryOptionsState by orderViewModel.deliveryOptions.observeAsState(DeliveryOptionsState.Loading)

    when (val currentState = deliveryOptionsState) {
        is DeliveryOptionsState.Loading -> LoadingScreen()
        is DeliveryOptionsState.Error -> Log.e("", "")
        is DeliveryOptionsState.Success -> CalculateDelivery(
            orderViewModel,
            currentState.points,
            currentState.packageTypes,
            onSelectDeliveryPointClick
        )
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = DeliveryTheme.colors.indicatorFocused)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateDelivery(
    orderViewModel: OrderViewModel,
    deliveryPints: List<DeliveryPoint>,
    packageTypes: List<PackageType>,
    onSelectDeliveryPointClick: (DeliveryPointType, List<DeliveryPoint>) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val orderState by orderViewModel.orderState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeliveryTheme.colors.backgroundSecondary)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Text(
            stringResource(R.string.calculate_delivery_screen_header),
            color = DeliveryTheme.colors.textPrimary,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            stringResource(R.string.calculete_delivery_screen_postheader),
            color = DeliveryTheme.colors.textTertiary,
            style = MaterialTheme.typography.titleSmall,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .clip(shape = RoundedCornerShape(24.dp))
                .background(DeliveryTheme.colors.backgroundPrimary)
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            Text(
                stringResource(R.string.calculete_delivery_title),
                Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.CenterHorizontally),
                color = DeliveryTheme.colors.textPrimary,
                style = MaterialTheme.typography.titleMedium,
            )
            Picker(
                stringResource(R.string.sender_city),
                orderState.senderDelivery?.name ?: stringResource(R.string.sender_city),
                painterResource(R.drawable.marker),
                { onSelectDeliveryPointClick(DeliveryPointType.SENDER_DELIVERY, deliveryPints) },
                variants = arrayListOf(
                    deliveryPints[0].name,
                    deliveryPints[1].name,
                    deliveryPints[2].name
                )
            )
            Picker(
                stringResource(R.string.receiver_city),
                orderState.receiverDelivery?.name ?: stringResource(R.string.receiver_city_hint),
                painterResource(R.drawable.pointer),
                { onSelectDeliveryPointClick(DeliveryPointType.RECEIVER_DELIVERY, deliveryPints) },
                variants = arrayListOf(
                    deliveryPints[1].name,
                    deliveryPints[2].name,
                    deliveryPints[3].name
                )
            )
            Picker(
                stringResource(R.string.package_size),
                orderState.packageType?.name ?: stringResource(R.string.package_type_hint),
                painterResource(R.drawable.email),
                { showBottomSheet = true }
            )
            BrandButton(stringResource(R.string.calculate_button), {})
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .clip(shape = RoundedCornerShape(24.dp))
                .background(DeliveryTheme.colors.backgroundPrimary)
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            Text(
                stringResource(R.string.track_package_title),
                modifier = Modifier.padding(bottom = 24.dp),
                color = DeliveryTheme.colors.textPrimary,
                style = MaterialTheme.typography.titleMedium,
            )
            BrandTextField("", {}, label = stringResource(R.string.order_number))
            BrandButton(stringResource(R.string.find_package_button), {})
        }
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                containerColor = DeliveryTheme.colors.backgroundPrimary,
                scrimColor = DeliveryTheme.colors.backgroundOverlay,
                sheetState = sheetState,
                dragHandle = null,
            ) {
                Text(
                    stringResource(R.string.package_size),
                    color = DeliveryTheme.colors.textPrimary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .background(DeliveryTheme.colors.backgroundPrimary)
                ) {
                    items(packageTypes) { item ->
                        PackageTypeItem(item, {
                            orderViewModel.setPackageType(item)
                            showBottomSheet = false
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun PackageTypeItem(item: PackageType, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick()
            }
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Text(
            "${item.name}, ${item.length}X${item.width}X${item.height}",
            style = MaterialTheme.typography.labelLarge,
            color = DeliveryTheme.colors.textPrimary,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}

