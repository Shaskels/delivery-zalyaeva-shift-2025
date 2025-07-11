package com.example.delivery_zalyaeva_shift_2025.components.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.delivery_zalyaeva_shift_2025.theme.DeliveryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryTopAppBar(title: String, icon: Painter, onIconClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                title,
                style = MaterialTheme.typography.titleMedium,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onIconClick() },
            ) {
                Icon(
                    painter = icon,
                    contentDescription = null
                )
            }
        },
        windowInsets = WindowInsets(
            top = 0.dp,
            bottom = 0.dp
        ),
        colors = TopAppBarColors(
            containerColor = DeliveryTheme.colors.backgroundPrimary,
            scrolledContainerColor = DeliveryTheme.colors.backgroundPrimary,
            navigationIconContentColor = DeliveryTheme.colors.borderMedium,
            titleContentColor = DeliveryTheme.colors.textPrimary,
            actionIconContentColor = DeliveryTheme.colors.backgroundPrimary
        ),
    )
}