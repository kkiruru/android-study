package com.kkiruru.navigation.ui.address

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kkiruru.navigation.ui.address.AddressDestinationsArgs.SEARCH_ONLY_MODE
import com.kkiruru.navigation.ui.address.AddressScreens.SEARCH_SCREEN
import com.kkiruru.navigation.ui.address.AddressScreens.SETTING_SCREEN


private object AddressScreens {
    const val SEARCH_SCREEN = "search"
    const val SETTING_SCREEN = "setting"
}

object AddressDestinationsArgs {
    const val SEARCH_ONLY_MODE = "isSearchOnly"
}

object AddressDestinations {
    const val SEARCH_ROUTE = SEARCH_SCREEN
    const val SETTING_ROUTE = SETTING_SCREEN
}

@Composable
fun AddressNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String,
    viewModel: AddressViewModel,
) {
    Log.e("AddressNavigation", "route ${startDestination}")

    NavHost(navController, startDestination = startDestination) {
        composable(
            route = AddressDestinations.SEARCH_ROUTE,
            arguments = listOf(navArgument(SEARCH_ONLY_MODE) {
                type = NavType.BoolType; defaultValue = false
            }),
        ) {
            val isSearchOnly = viewModel.isSearchOnlyMode.collectAsState()
            SearchScreen(
                gotoSettings = {
                    navController.navigateToSettingScreen()
                },
                isSearchOnly = isSearchOnly.value,
            )
        }
        composable(
            route = AddressDestinations.SETTING_ROUTE
        ) {
            SettingScreen(
                gotoSearch = {
                    navController.navigateToSearchScreen()
                }
            )
        }
    }
}

private fun NavHostController.navigateToSearchScreen() {
    this.navigate(
        AddressDestinations.SEARCH_ROUTE
    )
}

private fun NavHostController.navigateToSettingScreen() {
    this.navigate(
        AddressDestinations.SETTING_ROUTE
    )
}
