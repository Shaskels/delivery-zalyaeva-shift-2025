package com.example.delivery_zalyaeva_shift_2025.feature.calculation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.delivery_zalyaeva_shift_2025.components.ui.Banner
import com.example.delivery_zalyaeva_shift_2025.components.ui.DeliveryTopAppBar
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.Calculation
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.domain.entity.DeliveryType
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.presentation.OrderViewModel
import com.example.delivery_zalyaeva_shift_2025.components.ui.shimmerLoading
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.R
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.presentation.DeliveryCalculationViewModel
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.presentation.DeliveryCalculationsState
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.ui.OrderProgressBar
import com.example.delivery_zalyaeva_shift_2025.theme.DeliveryTheme

private const val STEP_NUMBER = 1
private const val NUMBER_OF_STEPS = 7

@Composable
fun DeliveryTypeScreen(
    deliveryCalculationViewModel: DeliveryCalculationViewModel,
    orderViewModel: OrderViewModel,
    onCancelAction: () -> Unit,
) {
    val deliveryCalculationsState by deliveryCalculationViewModel.deliveryCalculations.observeAsState(
        DeliveryCalculationsState.Loading
    )

    Column(modifier = Modifier.fillMaxSize()) {
        DeliveryTopAppBar(
            title = stringResource(R.string.delivery_type_title),
            icon = painterResource(R.drawable.ic_left),
            onIconClick = { onCancelAction() }
        )

        when (val currentState = deliveryCalculationsState) {
            is DeliveryCalculationsState.Loading -> DeliveryType(
                isLoading = true,
                orderViewModel = orderViewModel,
                emptyList(),
            )

            is DeliveryCalculationsState.Error -> com.example.delivery_zalyaeva_shift_2025.components.ui.ErrorScreen(
                stringResource(R.string.something_went_wrong)
            ) {
                deliveryCalculationViewModel.getDeliveryCalculations(orderViewModel.orderState.value)
            }

            is DeliveryCalculationsState.Content -> DeliveryType(
                isLoading = false,
                orderViewModel = orderViewModel,
                currentState.calculations,
            )
        }
    }

}

@Composable
fun DeliveryType(
    isLoading: Boolean,
    orderViewModel: OrderViewModel,
    calculations: List<Calculation>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .background(DeliveryTheme.colors.backgroundPrimary)
            .padding(horizontal = 16.dp)
    ) {

        OrderProgressBar(
            step = STEP_NUMBER,
            numberOfSteps = NUMBER_OF_STEPS,
        )

        if (!isLoading) {
            LazyColumn(modifier = Modifier.padding(bottom = 24.dp)) {
                items(calculations) { item ->
                    CalculationItem(
                        item = item,
                        onItemClick = { deliveryType ->
                            orderViewModel.setDeliveryType(
                                deliveryType
                            )
                        }
                    )
                }
            }
        } else {
            Column(modifier = Modifier.padding(bottom = 24.dp)) {
                repeat(2) {
                    CalculationItemHolder()
                }
            }
        }

        Banner(
            title = stringResource(R.string.banner2_title),
            text = stringResource(R.string.banner2_text),
            colors = DeliveryTheme.colors.banner2Colors
        )
    }
}

@Composable
fun CalculationItemHolder() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .height(120.dp)
            .clip(shape = RoundedCornerShape(24.dp))
            .shimmerLoading()
    )
}

@Composable
fun CalculationItem(item: Calculation, onItemClick: (DeliveryType) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .border(
                BorderStroke(1.dp, color = DeliveryTheme.colors.borderExtraLight),
                shape = RoundedCornerShape(24.dp)
            )
            .clickable { onItemClick(item.type) }
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(48.dp)
                    .clip(shape = RoundedCornerShape(40.dp))
                    .background(color = DeliveryTheme.colors.backgroundLight)
                    .align(Alignment.Top)
            ) {
                Icon(
                    when (item.type) {
                        DeliveryType.DEFAULT -> painterResource(R.drawable.bus)
                        DeliveryType.EXPRESS -> painterResource(R.drawable.plane)
                    },
                    tint = DeliveryTheme.colors.borderMedium,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    item.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = DeliveryTheme.colors.textTertiary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    "${item.price} ₽",
                    style = MaterialTheme.typography.bodyMedium,
                    color = DeliveryTheme.colors.textPrimary,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Text(
                    pluralStringResource(R.plurals.numberOfDays, item.days, item.days),
                    style = MaterialTheme.typography.bodySmall,
                    color = DeliveryTheme.colors.textTertiary,
                )
            }

            Icon(
                painter = painterResource(R.drawable.arrow_small_right),
                contentDescription = null,
                tint = DeliveryTheme.colors.borderMedium,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.Top)
            )
        }
    }
}
