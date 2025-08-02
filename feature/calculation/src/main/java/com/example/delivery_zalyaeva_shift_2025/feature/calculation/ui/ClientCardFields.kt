package com.example.delivery_zalyaeva_shift_2025.feature.calculation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.delivery_zalyaeva_shift_2025.components.ui.BrandTextField
import com.example.delivery_zalyaeva_shift_2025.feature.calculation.R

@Composable
fun ClientCardFields(
    onSurnameChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onPatronymicChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    modifier: Modifier
) {
    var clientSurname by rememberSaveable { mutableStateOf("") }
    var clientName by rememberSaveable { mutableStateOf("") }
    var clientPatronymic by rememberSaveable { mutableStateOf("") }
    var clientPhone by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {

        BrandTextField(
            text = clientSurname,
            onTextChange = {
                clientSurname = it
                onSurnameChange(it)
            },
            label = stringResource(R.string.surname)
        )

        Spacer(modifier = Modifier.padding(24.dp))

        BrandTextField(
            text = clientName,
            onTextChange = {
                clientName = it
                onNameChange(it)
            },
            label = stringResource(R.string.name)
        )

        Spacer(modifier = Modifier.padding(24.dp))

        BrandTextField(
            text = clientPatronymic,
            onTextChange = {
                clientPatronymic = it
                onPatronymicChange(it)
            },
            label = stringResource(R.string.patronymic)
        )

        Spacer(modifier = Modifier.padding(24.dp))

        BrandTextField(
            text = clientPhone,
            onTextChange = {
                clientPhone = it
                onPhoneChange(it)
            },
            label = stringResource(R.string.telephone)
        )
    }
}