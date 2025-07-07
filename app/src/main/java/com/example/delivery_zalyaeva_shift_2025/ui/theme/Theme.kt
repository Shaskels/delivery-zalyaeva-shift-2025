package com.example.delivery_zalyaeva_shift_2025.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = DeliveryColors(
    backgroundPrimary = BackgroundPrimary,
    backgroundSecondary = BackgroundSecondary,
    backgroundTertiary = BackgroundTertiary,
    backgroundDisable = BackgroundDisable,
    textPrimary = TextPrimary,
    textSecondary = TextSecondary,
    textTertiary = TextTertiary,
    textQuartenery = TextQuartenery,
    textInvert = TextInvert,
    textError = TextError,
    borderExtraLight = BorderExtraLight,
    borderLight = BorderLight,
    borderMedium = BorderMedium,
    indicatorWhite = IndicatorWhite,
    indicatorLight = IndicatorLight,
    indicatorMedium = IndicatorMedium,
    indicatorNormal = IndicatorNormal,
    indicatorError = IndicatorError,
    indicatorAttention = IndicatorAttention,
    indicatorPositive = IndicatorPositive,
    backgroundBrand = BackgroundBrand,
    backgroundPressedPrimary = BackgroundPressedPrimary,
    backgroundBrandExtraLight = BackgroundBrandExtraLight,
    backgroundHoverPrimary = BackgroundHoverPrimary,
    textBrandDisables = TextBrandDisabled,
    indicatorFocused = IndicatorFocused,
    indicatorFocusedAlternative = IndicatorFocusedAlternative,
    backgroundOverlay = BackgroundOverlay,
)

private val LightColorScheme = DeliveryColors(
    backgroundPrimary = BackgroundPrimary,
    backgroundSecondary = BackgroundSecondary,
    backgroundTertiary = BackgroundTertiary,
    backgroundDisable = BackgroundDisable,
    textPrimary = TextPrimary,
    textSecondary = TextSecondary,
    textTertiary = TextTertiary,
    textQuartenery = TextQuartenery,
    textInvert = TextInvert,
    textError = TextError,
    borderExtraLight = BorderExtraLight,
    borderLight = BorderLight,
    borderMedium = BorderMedium,
    indicatorWhite = IndicatorWhite,
    indicatorLight = IndicatorLight,
    indicatorMedium = IndicatorMedium,
    indicatorNormal = IndicatorNormal,
    indicatorError = IndicatorError,
    indicatorAttention = IndicatorAttention,
    indicatorPositive = IndicatorPositive,
    backgroundBrand = BackgroundBrand,
    backgroundPressedPrimary = BackgroundPressedPrimary,
    backgroundBrandExtraLight = BackgroundBrandExtraLight,
    backgroundHoverPrimary = BackgroundHoverPrimary,
    textBrandDisables = TextBrandDisabled,
    indicatorFocused = IndicatorFocused,
    indicatorFocusedAlternative = IndicatorFocusedAlternative,
    backgroundOverlay = BackgroundOverlay,
)

@Composable
fun DeliveryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    ProvideDeliveryColors(colors) {
        MaterialTheme(
            colorScheme = debugColors(darkTheme),
            typography = Typography,
            content = content
        )
    }
}

object DeliveryTheme {
    val colors: DeliveryColors
        @Composable
        get() = LocalDeliveryColors.current
}

@Immutable
data class DeliveryColors(
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val backgroundTertiary: Color,
    val backgroundDisable: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textQuartenery: Color,
    val textInvert: Color,
    val textError: Color,
    val borderExtraLight: Color,
    val borderLight: Color,
    val borderMedium: Color,
    val indicatorWhite: Color,
    val indicatorLight: Color,
    val indicatorMedium: Color,
    val indicatorNormal: Color,
    val indicatorError: Color,
    val indicatorAttention: Color,
    val indicatorPositive: Color,
    val backgroundBrand: Color,
    val backgroundPressedPrimary: Color,
    val backgroundHoverPrimary: Color,
    val backgroundBrandExtraLight: Color,
    val textBrandDisables: Color,
    val indicatorFocused: Color,
    val indicatorFocusedAlternative: Color,
    val backgroundOverlay: Color,
)

@Composable
fun ProvideDeliveryColors(colors: DeliveryColors, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalDeliveryColors provides colors, content = content)
}

private val LocalDeliveryColors = staticCompositionLocalOf<DeliveryColors> {
    error("No DeliveryColorPalette provided")
}

fun debugColors(darkTheme: Boolean, debugColor: Color = Color.Magenta) = ColorScheme(
    primary = debugColor,
    onPrimary = debugColor,
    primaryContainer = debugColor,
    onPrimaryContainer = debugColor,
    inversePrimary = debugColor,
    secondary = debugColor,
    onSecondary = debugColor,
    secondaryContainer = debugColor,
    onSecondaryContainer = debugColor,
    tertiary = debugColor,
    onTertiary = debugColor,
    tertiaryContainer = debugColor,
    onTertiaryContainer = debugColor,
    background = debugColor,
    onBackground = debugColor,
    surface = debugColor,
    onSurface = debugColor,
    surfaceVariant = debugColor,
    onSurfaceVariant = debugColor,
    surfaceTint = debugColor,
    inverseSurface = debugColor,
    inverseOnSurface = debugColor,
    error = debugColor,
    onError = debugColor,
    errorContainer = debugColor,
    onErrorContainer = debugColor,
    outline = debugColor,
    outlineVariant = debugColor,
    scrim = debugColor,
    surfaceBright = debugColor,
    surfaceDim = debugColor,
    surfaceContainer = debugColor,
    surfaceContainerHigh = debugColor,
    surfaceContainerHighest = debugColor,
    surfaceContainerLow = debugColor,
    surfaceContainerLowest = debugColor,
)