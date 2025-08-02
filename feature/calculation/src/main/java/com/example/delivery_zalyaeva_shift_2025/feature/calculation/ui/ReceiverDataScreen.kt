package com.example.delivery_zalyaeva_shift_2025.feature.calculation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.delivery_zalyaeva_shift_2025.components.ui.BrandButton
import com.example.delivery_zalyaeva_shift_2025.components.ui.DeliveryTopAppBar
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.R
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.presentation.Client
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.presentation.OrderViewModel
import com.example.delivery_zalyaeva_shift_2025.shared.calculation.ui.OrderProgressBar

@Composable
fun ReceiverDataScreen(
    orderViewModel: OrderViewModel,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        DeliveryTopAppBar(
            title = stringResource(R.string.receiver),
            icon = painterResource(R.drawable.go_back),
            onIconClick = onBackClick
        )
        ClientCard(
            orderViewModel = orderViewModel,
            onContinueClick = onContinueClick
        )
    }
}

@Composable
fun ClientCard(
    orderViewModel: OrderViewModel,
    onContinueClick: () -> Unit
) {
    var client = Client("", "", null, "")

    Column(modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)) {

        OrderProgressBar(2, 7)

        ClientCardFields(
            onSurnameChange = { surname -> client = client.copy(surname = surname) },
            onNameChange = { name -> client = client.copy(name = name) },
            onPatronymicChange = { patronymic -> client = client.copy(patronymic = patronymic) },
            onPhoneChange = { phone -> client = client.copy(phone = phone) },
            modifier = Modifier.padding(vertical = 24.dp)
        )

        Box(modifier = Modifier.padding(vertical = 16.dp)) {
            BrandButton(
                text = stringResource(R.string.continue_button),
                onClick = {
                    orderViewModel.setReceiverData(client)
                    onContinueClick()
                }
            )
        }

    }

}