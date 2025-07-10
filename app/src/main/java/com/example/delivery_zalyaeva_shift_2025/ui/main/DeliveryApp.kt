package com.example.delivery_zalyaeva_shift_2025.ui.main

import android.net.Uri
import android.os.Bundle
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.delivery_zalyaeva_shift_2025.R
import com.example.delivery_zalyaeva_shift_2025.presentation.DeliveryCalculationViewModel
import com.example.delivery_zalyaeva_shift_2025.presentation.OrderFindViewModel
import com.example.delivery_zalyaeva_shift_2025.presentation.OrderViewModel
import com.example.delivery_zalyaeva_shift_2025.ui.screens.history.HistoryRoute
import com.example.delivery_zalyaeva_shift_2025.ui.screens.profile.ProfileRoute
import com.example.delivery_zalyaeva_shift_2025.ui.screens.calculateDelivery.CalculateDeliveryRoute
import com.example.delivery_zalyaeva_shift_2025.ui.screens.calculateDelivery.CalculateDeliveryScreen
import com.example.delivery_zalyaeva_shift_2025.ui.screens.deliveryPoints.DeliveryPoints
import com.example.delivery_zalyaeva_shift_2025.ui.screens.deliveryPoints.DeliveryPointsRoute
import com.example.delivery_zalyaeva_shift_2025.ui.screens.deliveryPoints.DeliveryPointsScreen
import com.example.delivery_zalyaeva_shift_2025.ui.screens.deliveryType.DeliveryTypeRoute
import com.example.delivery_zalyaeva_shift_2025.ui.screens.deliveryType.DeliveryTypeScreen
import com.example.delivery_zalyaeva_shift_2025.ui.screens.history.HistoryScreen
import com.example.delivery_zalyaeva_shift_2025.ui.screens.profile.ProfileScreen
import com.example.delivery_zalyaeva_shift_2025.ui.theme.DeliveryTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel
import kotlin.reflect.KType
import kotlin.reflect.typeOf

@Composable
fun DeliveryApp(
    orderViewModel: OrderViewModel = koinViewModel(),
    orderFindViewModel: OrderFindViewModel = koinViewModel(),
    deliveryCalculationViewModel: DeliveryCalculationViewModel = koinViewModel()
) {
    val navController = rememberNavController()
    val selectedTab = rememberSaveable { mutableStateOf(NavigationOptions.CALCULATE_DELIVERY) }
    val isDestinationTab = rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(key1 = Unit) {
        orderViewModel.getDeliveryOptions()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val openedOption =
                NavigationOptions.entries.firstOrNull { destination.hasRoute(it.route) }

            if (openedOption != null) {
                isDestinationTab.value = true
                selectedTab.value = openedOption
            } else isDestinationTab.value = false
        }
    }

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
                        deliveryCalculationViewModel = deliveryCalculationViewModel,
                        orderViewModel = orderViewModel,
                        orderFindViewModel = orderFindViewModel,
                        onSelectDeliveryPointClick = { deliveryPointsType, deliveryPoints ->
                            navController.navigate(
                                DeliveryPointsRoute(
                                    DeliveryPoints(deliveryPointsType, deliveryPoints)
                                )
                            )
                        },
                        onCalculateClick = { navController.navigate(DeliveryTypeRoute) }
                    )
                }

                animatedComposable<DeliveryPointsRoute>(mapOf(typeOf<DeliveryPoints>() to DeliveryPointsType)) {
                    val destination = it.toRoute<DeliveryPointsRoute>()
                    DeliveryPointsScreen(
                        deliveryPoints = destination.deliveryPoints,
                        viewModel = orderViewModel,
                        onCancelAction = { navController.navigateUp() },
                        onDeliveryPointClick = { navController.navigateUp() }
                    )
                }

                animatedComposable<DeliveryTypeRoute> {
                    DeliveryTypeScreen(
                        deliveryCalculationViewModel = deliveryCalculationViewModel,
                        orderViewModel = orderViewModel,
                        onCancelAction = { navController.navigateUp() }
                    )
                }

                animatedComposable<ProfileRoute> {
                    ProfileScreen()
                }

                animatedComposable<HistoryRoute> {
                    HistoryScreen()
                }
            }

            if (isDestinationTab.value) {
                BottomNavigation(
                    navigationOptions = NavigationOptions.entries,
                    selectedNavigationOption = selectedTab.value,
                    onItemClicked = { navOption ->
                        when (navOption) {
                            NavigationOptions.CALCULATE_DELIVERY -> navController.openPoppingAllPrevious(
                                CalculateDeliveryRoute
                            )

                            NavigationOptions.PROFILE -> navController.openPoppingAllPrevious(
                                ProfileRoute
                            )

                            NavigationOptions.HISTORY -> navController.openPoppingAllPrevious(
                                HistoryRoute
                            )
                        }
                        selectedTab.value = navOption
                    }
                )
            }
        }
    }
}

inline fun <reified T : Any> NavGraphBuilder.animatedComposable(
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    noinline block: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable<T>(
        typeMap = typeMap,
        enterTransition = ENTER_TRANSITION,
        exitTransition = EXIT_TRANSITION,
        popEnterTransition = POP_ENTER_TRANSITION,
        popExitTransition = POP_EXIT_TRANSITION,
        content = block
    )
}

@Composable
private fun BottomNavigation(
    navigationOptions: List<NavigationOptions>,
    selectedNavigationOption: NavigationOptions,
    onItemClicked: (NavigationOptions) -> Unit,
) {
    NavigationBar(
        containerColor = DeliveryTheme.colors.backgroundPrimary,
        contentColor = DeliveryTheme.colors.borderMedium,
    ) {
        for (option in navigationOptions) {
            NavigationBarItem(
                selected = selectedNavigationOption == option,
                onClick = { onItemClicked(option) },
                icon = { Icon(painter = getIcon(option), "", modifier = Modifier.padding(0.dp)) },
                label = {
                    Text(
                        text = getLabel(option),
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = DeliveryTheme.colors.backgroundBrand,
                    selectedTextColor = DeliveryTheme.colors.backgroundBrand,
                    selectedIndicatorColor = DeliveryTheme.colors.backgroundPrimary,
                    unselectedIconColor = DeliveryTheme.colors.borderMedium,
                    unselectedTextColor = DeliveryTheme.colors.borderMedium,
                    disabledIconColor = DeliveryTheme.colors.borderLight,
                    disabledTextColor = DeliveryTheme.colors.borderLight,
                )
            )
        }
    }
}

@Composable
private fun getIcon(option: NavigationOptions): Painter = painterResource(
    when (option) {
        NavigationOptions.CALCULATE_DELIVERY -> R.drawable.calculate
        NavigationOptions.PROFILE -> R.drawable.user
        NavigationOptions.HISTORY -> R.drawable.time
    }
)

@Composable
private fun getLabel(option: NavigationOptions): String = stringResource(
    when (option) {
        NavigationOptions.CALCULATE_DELIVERY -> R.string.calculation
        NavigationOptions.PROFILE -> R.string.profile
        NavigationOptions.HISTORY -> R.string.history
    }
)

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

