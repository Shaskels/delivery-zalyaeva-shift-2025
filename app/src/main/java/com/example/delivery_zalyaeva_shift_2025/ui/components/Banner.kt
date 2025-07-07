package com.example.delivery_zalyaeva_shift_2025.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun Banner(banner: Painter){
    Image(
        painter = banner,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
    )
}