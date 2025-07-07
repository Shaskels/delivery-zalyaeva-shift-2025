package com.example.delivery_zalyaeva_shift_2025.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.delivery_zalyaeva_shift_2025.R
import com.example.delivery_zalyaeva_shift_2025.ui.theme.DeliveryTheme

@Composable
fun Picker(
    title: String,
    hint: String,
    icon: Painter,
    onClick: () -> Unit,
    variants: List<String>? = null,
) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            title,
            color = DeliveryTheme.colors.textSecondary,
            style = MaterialTheme.typography.labelMedium
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
                .background(DeliveryTheme.colors.backgroundPrimary)
                .border(
                    width = 1.dp,
                    color = DeliveryTheme.colors.borderLight,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(top = 8.dp, bottom = 8.dp, start = 12.dp, end = 10.dp)
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = DeliveryTheme.colors.borderMedium,
                modifier = Modifier
                    .padding(end = 2.dp)
            )

            Text(
                hint,
                modifier = Modifier
                    .align(Alignment.CenterVertically).weight(1f),
                color = DeliveryTheme.colors.textSecondary,
                style = MaterialTheme.typography.labelLarge
            )

            Icon(
                painter = painterResource(R.drawable.chevron_down),
                contentDescription = null,
                tint = DeliveryTheme.colors.borderMedium,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable { onClick() }
            )
        }
        if (variants != null ) {
            CommonVariantsPicker(variants)
        }
    }
}

@Composable
fun CommonVariantsPicker(variants: List<String>) {
    Row(modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)) {
        for (element in variants) {
            Text(
                element,
                color = DeliveryTheme.colors.textTertiary,
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}