package com.example.delivery_zalyaeva_shift_2025.ui.screens.calculateDelivery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.delivery_zalyaeva_shift_2025.R
import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.domain.entity.PackageType
import com.example.delivery_zalyaeva_shift_2025.presentation.DeliveryCalculationViewModel
import com.example.delivery_zalyaeva_shift_2025.presentation.DeliveryOptionsState
import com.example.delivery_zalyaeva_shift_2025.presentation.OrderFindViewModel
import com.example.delivery_zalyaeva_shift_2025.presentation.OrderViewModel
import com.example.delivery_zalyaeva_shift_2025.ui.components.Banner
import com.example.delivery_zalyaeva_shift_2025.ui.components.BrandButton
import com.example.delivery_zalyaeva_shift_2025.ui.components.BrandTextField
import com.example.delivery_zalyaeva_shift_2025.ui.components.ErrorScreen
import com.example.delivery_zalyaeva_shift_2025.ui.components.LoadingScreen
import com.example.delivery_zalyaeva_shift_2025.ui.components.Picker
import com.example.delivery_zalyaeva_shift_2025.ui.screens.deliveryPoints.DeliveryPointType
import com.example.delivery_zalyaeva_shift_2025.ui.theme.DeliveryTheme

@Composable
fun CalculateDeliveryScreen(
    deliveryCalculationViewModel: DeliveryCalculationViewModel,
    orderViewModel: OrderViewModel,
    orderFindViewModel: OrderFindViewModel,
    onSelectDeliveryPointClick: (DeliveryPointType, List<DeliveryPoint>) -> Unit,
    onCalculateClick: () -> Unit,
) {
    val deliveryOptionsState by orderViewModel.deliveryOptions.observeAsState(DeliveryOptionsState.Loading)

    when (val currentState = deliveryOptionsState) {
        is DeliveryOptionsState.Loading -> LoadingScreen()
        is DeliveryOptionsState.Error -> ErrorScreen(stringResource(R.string.something_went_wrong)) { orderViewModel.getDeliveryOptions() }
        is DeliveryOptionsState.Content -> CalculateDelivery(
            deliveryCalculationViewModel = deliveryCalculationViewModel,
            orderViewModel = orderViewModel,
            orderFindViewModel = orderFindViewModel,
            deliveryPoints = currentState.points,
            packageTypes = currentState.packageTypes,
            onSelectDeliveryPointClick = onSelectDeliveryPointClick,
            onCalculateClick = onCalculateClick,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateDelivery(
    deliveryCalculationViewModel: DeliveryCalculationViewModel,
    orderViewModel: OrderViewModel,
    orderFindViewModel: OrderFindViewModel,
    deliveryPoints: List<DeliveryPoint>,
    packageTypes: List<PackageType>,
    onSelectDeliveryPointClick: (DeliveryPointType, List<DeliveryPoint>) -> Unit,
    onCalculateClick: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

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

        CalculateDeliveryBox(
            deliveryPoints = deliveryPoints,
            deliveryCalculationViewModel = deliveryCalculationViewModel,
            orderViewModel = orderViewModel,
            onSelectDeliveryPointClick = onSelectDeliveryPointClick,
            onCalculateClick = onCalculateClick,
            onPackageSizeSelect = { showBottomSheet = true }
        )

        FindPackageBox(orderFindViewModel = orderFindViewModel)

        Banner(banner = painterResource(R.drawable.banner1))

        if (showBottomSheet) {
            PackageTypeBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false },
                packageTypes = packageTypes,
                onPackageTypeSelect = { item ->
                    orderViewModel.setPackageType(item)
                    showBottomSheet = false
                }
            )
        }
    }
}

@Composable
fun CalculateDeliveryBox(
    deliveryPoints: List<DeliveryPoint>,
    deliveryCalculationViewModel: DeliveryCalculationViewModel,
    orderViewModel: OrderViewModel,
    onSelectDeliveryPointClick: (DeliveryPointType, List<DeliveryPoint>) -> Unit,
    onCalculateClick: () -> Unit,
    onPackageSizeSelect: () -> Unit,
) {
    val orderState by orderViewModel.orderState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .clip(shape = RoundedCornerShape(24.dp))
            .background(DeliveryTheme.colors.backgroundPrimary)
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Text(
            text = stringResource(R.string.calculete_delivery_title),
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(Alignment.CenterHorizontally),
            color = DeliveryTheme.colors.textPrimary,
            style = MaterialTheme.typography.titleMedium,
        )

        Picker(
            title = stringResource(R.string.sender_city),
            hint = orderState.senderDelivery?.name ?: stringResource(R.string.sender_city),
            icon = painterResource(R.drawable.marker),
            onClick = {
                onSelectDeliveryPointClick(
                    DeliveryPointType.SENDER_DELIVERY,
                    deliveryPoints
                )
            },
            variants = arrayListOf(
                deliveryPoints[0].name,
                deliveryPoints[1].name,
                deliveryPoints[2].name
            ),
            onVariantClick = { variantIndex ->
                orderViewModel.setSenderDeliveryPoint(deliveryPoints[variantIndex])
            }
        )

        Picker(
            title = stringResource(R.string.receiver_city),
            hint = orderState.receiverDelivery?.name
                ?: stringResource(R.string.receiver_city_hint),
            icon = painterResource(R.drawable.pointer),
            onClick = {
                onSelectDeliveryPointClick(
                    DeliveryPointType.RECEIVER_DELIVERY,
                    deliveryPoints
                )
            },
            variants = arrayListOf(
                deliveryPoints[1].name,
                deliveryPoints[2].name,
                deliveryPoints[3].name
            ),
            onVariantClick = { variantIndex ->
                orderViewModel.setReceiverDeliveryPoint(deliveryPoints[variantIndex + 1])
            }
        )

        Picker(
            title = stringResource(R.string.package_size),
            hint = orderState.packageType?.name ?: stringResource(R.string.package_type_hint),
            icon = painterResource(R.drawable.email),
            onClick = onPackageSizeSelect
        )

        BrandButton(
            text = stringResource(R.string.calculate_button),
            onClick = {
                deliveryCalculationViewModel.getDeliveryCalculations(orderViewModel.orderState.value)
                onCalculateClick()
            })
    }
}

@Composable
fun FindPackageBox(orderFindViewModel: OrderFindViewModel) {
    var packageNumber by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 24.dp)
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

        BrandTextField(
            value = packageNumber,
            onValueChange = { packageNumber = it },
            label = stringResource(R.string.order_number)
        )

        BrandButton(stringResource(R.string.find_package_button), { orderFindViewModel.findOrder(packageNumber.text) })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackageTypeBottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    packageTypes: List<PackageType>,
    onPackageTypeSelect: (PackageType) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
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
                PackageTypeItem(item, { onPackageTypeSelect(item) })
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


