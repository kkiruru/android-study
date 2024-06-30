package com.kkiruru.navigation.ui.address

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    const val SEARCH_ROUTE = "$SEARCH_SCREEN/{$SEARCH_ONLY_MODE}"
    const val SETTING_ROUTE = SETTING_SCREEN
}

@Composable
fun AddressNavigation(navController: NavHostController, route: String) {
    Log.e("AddressNavigation", "route ${route}")

    NavHost(navController, startDestination = route) {
        composable(
            route = AddressDestinations.SEARCH_ROUTE,
            arguments = listOf(navArgument(SEARCH_ONLY_MODE) {
                type = NavType.BoolType; defaultValue = false
            }),
        ) {
            backStackEntry ->
            Log.e("SearchScreen", "SearchScreen ${backStackEntry.arguments}")

            val isSearchOnly = backStackEntry.arguments?.getBoolean(SEARCH_ONLY_MODE) ?: false
            SearchScreen(
                isSearchOnly,
                gotoSettings = {
                    navController.navigateToSettingScreen()
                }
            )
        }
        composable(
            route = AddressDestinations.SETTING_ROUTE
        ) {
            SettingScreen(
                gotoSearch = { isSearchOnly ->
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