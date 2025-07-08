package com.example.delivery_zalyaeva_shift_2025.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.delivery_zalyaeva_shift_2025.ui.theme.DeliveryTheme

@Composable
fun BrandTextField(value: TextFieldValue,onValueChange: (TextFieldValue)-> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = RoundedCornerShape(8.dp),
        enabled = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = DeliveryTheme.colors.textSecondary,
            unfocusedTextColor = DeliveryTheme.colors.textTertiary,
            errorTextColor = DeliveryTheme.colors.textError,
            cursorColor = DeliveryTheme.colors.textSecondary,
            focusedLabelColor = DeliveryTheme.colors.textTertiary,
            unfocusedLabelColor = DeliveryTheme.colors.textTertiary,
            focusedBorderColor = DeliveryTheme.colors.borderExtraLight,
            unfocusedBorderColor = DeliveryTheme.colors.borderLight,
            selectionColors = TextSelectionColors(
                handleColor = DeliveryTheme.colors.backgroundBrand,
                backgroundColor = DeliveryTheme.colors.indicatorFocusedAlternative
            )
        ),
        modifier = Modifier.fillMaxWidth()
    )
}