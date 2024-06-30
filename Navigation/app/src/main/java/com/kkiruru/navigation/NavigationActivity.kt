package com.kkiruru.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class NavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tab =
            savedInstanceState?.getString(INITIALIZED_TAB) ?: intent.extras?.getString(
                INITIALIZED_TAB
            )

        setContent {
            val navController = rememberNavController()
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        navController = navController,
                        route = tab ?: NavigationItem.Home.route,
                    )
                }
            }
        }
    }

    companion object {
        private const val INITIALIZED_TAB = "INITIALIZED_TAB"

        fun startActivity(
            context: Context,
            initialTab: NavigationItem = NavigationItem.Home,
        ) {
            context.startActivity(
                Intent(context, NavigationActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    putExtras(
                        Bundle().apply {
                            putString(INITIALIZED_TAB, initialTab.route)
                        }
                    )
                }
            )
        }
    }
}


@Composable
fun MainScreen(
    navController: NavHostController,
    route: String,
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(56.dp),
                containerColor = Color.Yellow,
                tonalElevation = 0.dp,
                contentPadding = PaddingValues(
                    start = 0.dp,
                    top = 0.dp,
                    end = 0.dp
                ),
            ) {
                BottomNavigationBar(
                    navController = navController,
                    route = route
                )
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(
                PaddingValues(
                    0.dp,
                    0.dp,
                    0.dp,
                    innerPadding.calculateBottomPadding()
                )
            )
        ) {
            MainAppNavigation(
                navController = navController,
                route = route
            )
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController, route: String) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Store,
        NavigationItem.Laundry,
        NavigationItem.My
    )
    var selectedItem by remember { mutableIntStateOf(0) }
    var currentRoute by remember { mutableStateOf(route) }

    items.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            selectedItem = index
        }
    }

    Log.e("Tag", "selectedItem ${selectedItem}")

    NavigationBar2 {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                modifier = Modifier.wrapContentHeight(),
                alwaysShowLabel = true,
                icon = {
                    Image(
                        modifier = Modifier.height(24.dp).width(25.dp),
                        painter = painterResource(if (selectedItem == index) item.selectedIcon else item.unselectedIcon),
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    currentRoute = item.route
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = Color(0xff51CA97),
                    selectedTextColor = Color(0xFF51CA97),
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = Color(0xFFC9C9C9),
                    unselectedTextColor = Color(0xFFC9C9C9),
                    disabledIconColor = Color.Black,
                    disabledTextColor = Color.Black,
                )
            )
        }
    }
}


@Composable
fun NavigationBar2(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    contentColor: Color = Color.White,
    tonalElevation: Dp = 0.dp,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
//                .windowInsetsPadding(windowInsets)
//                .defaultMinSize(minHeight = 38.dp)
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}


@Composable
fun MainAppNavigation(navController: NavHostController, route: String) {
    NavHost(navController, startDestination = route) {
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
        composable(NavigationItem.Store.route) {
            StoreScreen()
        }
        composable(NavigationItem.Laundry.route) {
            LaundryScreen()
        }
        composable(NavigationItem.My.route) {
            MyScreen()
        }
    }
}


@Composable
fun HomeScreen() {
    CenterText(text = "Home")
}

@Composable
fun StoreScreen() {
    CenterText(text = "Store")
}

@Composable
fun LaundryScreen() {
    CenterText(text = "Laundry")
}

@Composable
fun MyScreen() {
    CenterText(text = "My")
}

@Composable
fun CenterText(text: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, fontSize = 32.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun CheckboxText() {
    MaterialTheme {
        val navController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreen(
                navController = navController,
                route = NavigationItem.Home.route,
            )
        }
    }
}


sealed class NavigationItem(var route: String, @DrawableRes val selectedIcon: Int, @DrawableRes val unselectedIcon: Int, var title: String) {
    data object Home : NavigationItem("Home", R.drawable.ic_menu_home_activated, R.drawable.ic_menu_home_default, "홈")
    data object Store : NavigationItem("Store", R.drawable.ic_menu_store_activated, R.drawable.ic_menu_store_default, "스토어")
    data object Laundry : NavigationItem("Laundry", R.drawable.ic_menu_laundry_activated, R.drawable.ic_menu_laundry_default, "수거신청")
    data object My : NavigationItem("My", R.drawable.ic_menu_my_activated, R.drawable.ic_menu_my_default, "MY")
}