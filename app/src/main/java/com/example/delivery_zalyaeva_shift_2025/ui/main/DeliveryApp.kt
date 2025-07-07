package com.example.delivery_zalyaeva_shift_2025.ui.main

import android.net.Uri
import android.os.Bundle
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.delivery_zalyaeva_shift_2025.R
import com.example.delivery_zalyaeva_shift_2025.presentation.OrderViewModel
import com.example.delivery_zalyaeva_shift_2025.ui.screens.CalculateDeliveryRoute
import com.example.delivery_zalyaeva_shift_2025.ui.screens.CalculateDeliveryScreen
import com.example.delivery_zalyaeva_shift_2025.ui.screens.DeliveryPoints
import com.example.delivery_zalyaeva_shift_2025.ui.screens.DeliveryPointsRoute
import com.example.delivery_zalyaeva_shift_2025.ui.screens.DeliveryPointsScreen
import com.example.delivery_zalyaeva_shift_2025.ui.theme.DeliveryTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.KType
import kotlin.reflect.typeOf

@Composable
fun DeliveryApp(
    orderViewModel: OrderViewModel = koinViewModel()
) {
    val navController = rememberNavController()

    Scaffold(
        containerColor = DeliveryTheme.colors.backgroundPrimary
    ) { paddingValues: PaddingValues ->
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = navController,
                startDestination = CalculateDeliveryRoute,
            ) {
                animatedComposable<CalculateDeliveryRoute> {
                    CalculateDeliveryScreen(
                        orderViewModel,
                        onSelectDeliveryPointClick = { deliveryPointsType, deliveryPoints ->
                            navController.navigate(
                                DeliveryPointsRoute(
                                    DeliveryPoints(deliveryPointsType, deliveryPoints)
                                )
                            )
                        })
                }
                animatedComposable<DeliveryPointsRoute>(mapOf(typeOf<DeliveryPoints>() to DeliveryPointsType)) {
                    val destination = it.toRoute<DeliveryPointsRoute>()
                    DeliveryPointsScreen(
                        deliveryPoints = destination.deliveryPoints,
                        orderViewModel,
                        onCancelAction = { navController.navigateUp() },
                        onDeliveryPointClick = { navController.navigateUp() }
                    )
                }
            }
        }
    }
}

inline fun <reified T : Any> NavGraphBuilder.animatedComposable(
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>>? = null,
    noinline block: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable<T>(
        typeMap = typeMap ?: emptyMap(),
        enterTransition = ENTER_TRANSITION,
        exitTransition = EXIT_TRANSITION,
        popEnterTransition = POP_ENTER_TRANSITION,
        popExitTransition = POP_EXIT_TRANSITION,
        content = block
    )
}

fun NavController.openPoppingAllPrevious(route: Any) {
    this.navigate(route) {
        popUpTo(graph.startDestinationId)
        launchSingleTop = true
    }
}

val DeliveryPointsType = object : NavType<DeliveryPoints>(
    isNullableAllowed = false
) {
    override fun put(bundle: Bundle, key: String, value: DeliveryPoints) {
        bundle.putParcelable(key, value)
    }

    override fun get(bundle: Bundle, key: String): DeliveryPoints {
        return bundle.getParcelable<DeliveryPoints>(key) as DeliveryPoints
    }

    override fun serializeAsValue(value: DeliveryPoints): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun parseValue(value: String): DeliveryPoints {
        return Json.decodeFromString<DeliveryPoints>(value)
    }
}