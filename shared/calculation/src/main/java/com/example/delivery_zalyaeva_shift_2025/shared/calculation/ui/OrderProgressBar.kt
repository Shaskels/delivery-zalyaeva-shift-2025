package com.example.delivery_zalyaeva_shift_2025.shared.calculation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.R
import com.example.delivery_zalyaeva_shift_2025.theme.DeliveryTheme

@Composable
fun OrderProgressBar(step: Int, numberOfSteps: Int) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            "${stringResource(R.string.step)} $step ${stringResource(R.string.step_of)} $numberOfSteps",
            style = MaterialTheme.typography.bodySmall,
            color = DeliveryTheme.colors.textPrimary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LinearProgressIndicator(
            progress = { step.toFloat() / numberOfSteps },
            color = DeliveryTheme.colors.indicatorPositive,
            trackColor = DeliveryTheme.colors.indicatorLight,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))
        )
    }
}