package com.example.delivery_zalyaeva_shift_2025.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.delivery_zalyaeva_shift_2025.R
import com.example.delivery_zalyaeva_shift_2025.ui.theme.DeliveryTheme

@Composable
fun BrandButton(text: String, onClick: () -> Unit){
    Button(
        onClick = {onClick()},
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth(),
        enabled = true,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonColors(
            containerColor = DeliveryTheme.colors.backgroundBrand,
            contentColor = DeliveryTheme.colors.textInvert,
            disabledContainerColor = DeliveryTheme.colors.backgroundDisable,
            disabledContentColor = DeliveryTheme.colors.textBrandDisables,
        ),
        contentPadding = PaddingValues(vertical = 16.dp),
    ) {
        Text(
            text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}