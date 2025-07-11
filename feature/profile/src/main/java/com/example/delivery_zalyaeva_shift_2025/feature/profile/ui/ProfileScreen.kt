package com.example.delivery_zalyaeva_shift_2025.feature.profile.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.delivery_zalyaeva_shift_2025.feature.profile.R

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            stringResource(R.string.profile),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}