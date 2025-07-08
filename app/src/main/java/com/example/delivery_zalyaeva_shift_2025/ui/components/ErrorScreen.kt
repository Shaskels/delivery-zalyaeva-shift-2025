package com.example.delivery_zalyaeva_shift_2025.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.delivery_zalyaeva_shift_2025.R
import com.example.delivery_zalyaeva_shift_2025.ui.theme.DeliveryTheme

@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit) {
    val openAlertDialog = remember { mutableStateOf(true) }

    if (openAlertDialog.value) {
        AlertDialog(
            onDismissRequest = { openAlertDialog.value = false },
            title = {
                Text(
                    text = stringResource(id = R.string.error_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = DeliveryTheme.colors.textPrimary,
                )
            },
            text = {
                Text(
                    text = message,
                    style = MaterialTheme.typography.labelLarge,
                    color = DeliveryTheme.colors.textTertiary,
                )
            },
            confirmButton = {
                Button(
                    onClick = onRetry,
                    colors = ButtonColors(
                        containerColor = DeliveryTheme.colors.backgroundBrand,
                        contentColor = DeliveryTheme.colors.textInvert,
                        disabledContainerColor = DeliveryTheme.colors.backgroundDisable,
                        disabledContentColor = DeliveryTheme.colors.textBrandDisables,
                    )
                ) {
                    Text(text = stringResource(id = R.string.error_try_again))
                }
            },
            containerColor = DeliveryTheme.colors.backgroundPrimary,
            titleContentColor = DeliveryTheme.colors.textPrimary,
            textContentColor = DeliveryTheme.colors.textPrimary
        )
    }
}