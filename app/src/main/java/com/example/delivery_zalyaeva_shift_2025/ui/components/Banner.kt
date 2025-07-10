package com.example.delivery_zalyaeva_shift_2025.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.delivery_zalyaeva_shift_2025.R
import com.example.delivery_zalyaeva_shift_2025.ui.theme.DeliveryTheme

@Composable
fun Banner(title: String, text: String, colors: List<Color>) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(88.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(
                Brush.horizontalGradient(
                    colors
                )
            )
    ) {
        Column(Modifier.padding(top = 16.dp, start = 16.dp).weight(1f)) {
            Text(
                title,
                style = MaterialTheme.typography.bodyMedium,
                color = DeliveryTheme.colors.textInvert,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text,
                style = MaterialTheme.typography.labelMedium,
                color = DeliveryTheme.colors.textInvertLight,
            )
        }
        Image(
            painter = painterResource(R.drawable.gifts),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Bottom)
        )
    }
}