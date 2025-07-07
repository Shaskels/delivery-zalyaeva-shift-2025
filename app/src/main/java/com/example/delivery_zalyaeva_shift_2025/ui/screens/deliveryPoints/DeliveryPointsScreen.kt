package com.example.delivery_zalyaeva_shift_2025.ui.screens.deliveryPoints

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.delivery_zalyaeva_shift_2025.R
import com.example.delivery_zalyaeva_shift_2025.domain.entity.DeliveryPoint
import com.example.delivery_zalyaeva_shift_2025.presentation.OrderViewModel
import com.example.delivery_zalyaeva_shift_2025.ui.components.DeliveryTopAppBar
import com.example.delivery_zalyaeva_shift_2025.ui.theme.DeliveryTheme

@Composable
fun DeliveryPointsScreen(
    deliveryPoints: DeliveryPoints,
    viewModel: OrderViewModel,
    onCancelAction: () -> Unit,
    onDeliveryPointClick: () -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(DeliveryTheme.colors.backgroundPrimary)) {
        DeliveryTopAppBar(
            title = when (deliveryPoints.deliveryPointsType) {
                DeliveryPointType.RECEIVER_DELIVERY -> stringResource(R.string.delivery_points_where)
                DeliveryPointType.SENDER_DELIVERY -> stringResource(R.string.delivery_points_from)
            },
            icon = painterResource(R.drawable.ic_left),
            onIconClick = { onCancelAction() }
        )

        DeliveryPointsList(deliveryPoints, viewModel, onDeliveryPointClick)
    }
}

@Composable
fun DeliveryPointsList(
    deliveryPoints: DeliveryPoints,
    viewModel: OrderViewModel,
    onDeliveryPointClick: () -> Unit
) {
    LazyColumn {
        items(deliveryPoints.deliveryPoints) { item ->
            DeliveryPointItem(item, {
                when (deliveryPoints.deliveryPointsType) {
                    DeliveryPointType.RECEIVER_DELIVERY -> viewModel.setReceiverDeliveryPoint(item)
                    DeliveryPointType.SENDER_DELIVERY -> viewModel.setSenderDeliveryPoint(item)
                }
                onDeliveryPointClick()
            })
        }
    }
}

@Composable
fun DeliveryPointItem(
    item: DeliveryPoint,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick()
            }
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Text(
            item.name,
            style = MaterialTheme.typography.labelLarge,
            color = DeliveryTheme.colors.textPrimary,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )

        Icon(
            painter = painterResource(R.drawable.arrow_small_right),
            contentDescription = null,
            tint = DeliveryTheme.colors.borderMedium,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}