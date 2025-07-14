package com.example.delivery_zalyaeva_shift_2025.ui.screens.calculateDelivery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.delivery_zalyaeva_shift_2025.R
import com.example.delivery_zalyaeva_shift_2025.components.ui.Banner
import com.example.delivery_zalyaeva_shift_2025.components.ui.BrandButton
import com.example.delivery_zalyaeva_shift_2025.components.ui.BrandTextField
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.PackageType
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.presentation.OrderViewModel
import com.example.delivery_zalyaeva_shift_2025.components.ui.ErrorScreen
import com.example.delivery_zalyaeva_shift_2025.components.ui.LoadingScreen
import com.example.delivery_zalyaeva_shift_2025.components.ui.Picker
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.presentation.DeliveryOptionsState
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.presentation.DeliveryOptionsViewModel
import com.example.delivery_zalyaeva_shift_2025.feature.calculateDelivery.ui.DeliveryPointType
import com.example.delivery_zalyaeva_shift_2025.feature.findPackage.presentation.PackageFindViewModel
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.presentation.Order
import com.example.delivery_zalyaeva_shift_2025.theme.DeliveryTheme
import com.example.delivery_zalyaeva_shift_2025.util.list.getElements

private const val NUMBER_OF_PICKER_VARIANTS = 3

@Composable
fun CalculateDeliveryScreen(
    deliveryOptionsViewModel: DeliveryOptionsViewModel,
    orderViewModel: OrderViewModel,
    packageFindViewModel: PackageFindViewModel,
    onSelectDeliveryPointClick: (DeliveryPointType, List<DeliveryPoint>) -> Unit,
    onCalculateClick: () -> Unit,
) {
    val deliveryOptionsState by deliveryOptionsViewModel.deliveryOptions.observeAsState(
        DeliveryOptionsState.Loading
    )

    when (val currentState = deliveryOptionsState) {
        is DeliveryOptionsState.Loading -> LoadingScreen()
        is DeliveryOptionsState.Error -> ErrorScreen(
            message = stringResource(R.string.something_went_wrong),
            onRetry = { deliveryOptionsViewModel.getDeliveryOptions() })

        is DeliveryOptionsState.Content -> {
            orderViewModel.initOrder(
                Order(
                    senderDelivery = currentState.points.firstOrNull(),
                    receiverDelivery = currentState.points.getOrNull(1),
                    packageType = currentState.packageTypes.firstOrNull(),
                    deliveryType = null
                )
            )
            CalculateDelivery(
                orderViewModel = orderViewModel,
                packageFindViewModel = packageFindViewModel,
                deliveryPoints = currentState.points,
                packageTypes = currentState.packageTypes,
                onSelectDeliveryPointClick = onSelectDeliveryPointClick,
                onCalculateClick = onCalculateClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateDelivery(
    orderViewModel: OrderViewModel,
    packageFindViewModel: PackageFindViewModel,
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
            orderViewModel = orderViewModel,
            onSelectDeliveryPointClick = onSelectDeliveryPointClick,
            onCalculateClick = onCalculateClick,
            onPackageSizeSelect = { showBottomSheet = true }
        )

        FindPackageBox(packageFindViewModel = packageFindViewModel)

        Banner(
            title = stringResource(R.string.banner1_title),
            text = stringResource(R.string.banner1_text),
            colors = DeliveryTheme.colors.banner1Colors
        )

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
            hint = orderState.senderDelivery?.name ?: stringResource(R.string.sender_city_hint),
            icon = painterResource(R.drawable.marker),
            onClick = {
                onSelectDeliveryPointClick(
                    DeliveryPointType.SENDER_DELIVERY,
                    deliveryPoints
                )
            },
            variants = deliveryPoints.getElements(NUMBER_OF_PICKER_VARIANTS, 0).map { it.name },
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
            variants = deliveryPoints.getElements(NUMBER_OF_PICKER_VARIANTS, 1).map { it.name },
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

        Spacer(Modifier.padding(top = 24.dp))

        BrandButton(
            text = stringResource(R.string.calculate_button),
            onClick = {
                onCalculateClick()
            }
        )
    }
}

@Composable
fun FindPackageBox(packageFindViewModel: PackageFindViewModel) {
    var packageNumber by rememberSaveable { mutableStateOf("") }

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

        Spacer(Modifier.padding(top = 24.dp))

        BrandButton(
            stringResource(R.string.find_package_button),
            { packageFindViewModel.findOrder(packageNumber) })
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


