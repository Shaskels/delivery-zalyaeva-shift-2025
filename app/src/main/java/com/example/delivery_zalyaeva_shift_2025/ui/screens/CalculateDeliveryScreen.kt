package com.example.delivery_zalyaeva_shift_2025.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.delivery_zalyaeva_shift_2025.R
import com.example.delivery_zalyaeva_shift_2025.ui.components.BrandButton
import com.example.delivery_zalyaeva_shift_2025.ui.components.BrandTextField
import com.example.delivery_zalyaeva_shift_2025.ui.components.Picker
import com.example.delivery_zalyaeva_shift_2025.ui.theme.DeliveryTheme

@Preview
@Composable
fun CalculateDeliveryScreen() {
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .clip(shape = RoundedCornerShape(24.dp))
                .background(DeliveryTheme.colors.backgroundPrimary)
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            Text(
                stringResource(R.string.calculete_delivery_title),
                Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.CenterHorizontally),
                color = DeliveryTheme.colors.textPrimary,
                style = MaterialTheme.typography.titleMedium,
            )
            Picker(
                stringResource(R.string.sender_city),
                stringResource(R.string.sender_city_hint),
                painterResource(R.drawable.marker),
                {},
                variants = arrayListOf("Москва", "Москва", "Москва")
            )
            Picker(
                stringResource(R.string.receiver_city),
                stringResource(R.string.sender_city_hint),
                painterResource(R.drawable.pointer),
                {},
                variants = arrayListOf("Москва", "Москва", "Москва")
            )
            Picker(
                stringResource(R.string.package_size), "Конверт", painterResource(R.drawable.email), {}
            )
            BrandButton(stringResource(R.string.calculate_button), {})
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .clip(shape = RoundedCornerShape(24.dp))
                .background(DeliveryTheme.colors.backgroundPrimary)
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            Text(
                stringResource(R.string.track_package_title),
                modifier = Modifier.padding(bottom = 24.dp),
                style = MaterialTheme.typography.titleMedium,
            )
            BrandTextField("", {}, label = stringResource(R.string.order_number))
            BrandButton(stringResource(R.string.find_package_button), {})
        }
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
        )
    }
}

