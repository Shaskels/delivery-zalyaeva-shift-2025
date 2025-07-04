package com.example.delivery_zalyaeva_shift_2025.ui.main

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.delivery_zalyaeva_shift_2025.presentation.OrderViewModel
import com.example.delivery_zalyaeva_shift_2025.ui.screens.CalculateDeliveryRoute
import com.example.delivery_zalyaeva_shift_2025.ui.screens.CalculateDeliveryScreen
import com.example.delivery_zalyaeva_shift_2025.ui.theme.DeliveryTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DeliveryApp(
    orderViewModel: OrderViewModel = koinViewModel()
) {
    val navController = rememberNavController()

    Scaffold(containerColor = DeliveryTheme.colors.backgroundSecondary) { paddingValues: PaddingValues ->
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = navController,
                startDestination = CalculateDeliveryRoute,
            ) {
                animatedComposable<CalculateDeliveryRoute> {
                    CalculateDeliveryScreen(orderViewModel)
                }
            }
        }
    }
}

inline fun <reified T : Any> NavGraphBuilder.animatedComposable(noinline block: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit) {
    composable<T>(
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