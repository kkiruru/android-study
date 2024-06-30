package com.kkiruru.navigation.ui.address

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


class AddressActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isSearchOnly =
            savedInstanceState?.getBoolean(SEARCH_MODE) ?: intent.extras?.getBoolean(
                SEARCH_MODE
            ) ?: false

        val isSetting =
            savedInstanceState?.getBoolean(SETTING_MODE) ?: intent.extras?.getBoolean(
                SETTING_MODE
            ) ?: false

        val startDestination = if (isSetting) {
            AddressDestinations.SETTING_ROUTE
        }
        else {
            "search/$isSearchOnly"
        }

        setContent {
            val navController = rememberNavController()
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddressApp(
                        navController = navController,
                        route = startDestination)
                }
            }
        }

        Log.e("AddressActivity", "${startDestination}")
    }

    companion object {
        private const val SEARCH_MODE = "SEARCH_MODE"
        private const val SETTING_MODE = "SETTING_MODE"

        fun startActivity(
            context: Context,
            searchOnly: Boolean = false,
        ) {
            context.startActivity(
                Intent(context, AddressActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    putExtras(
                        Bundle().apply {
                            putBoolean(SEARCH_MODE, searchOnly)
                        }
                    )
                }
            )
        }

        fun startSettingActivity(
            context: Context,
        ) {
            context.startActivity(
                Intent(context, AddressActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    putExtras(
                        Bundle().apply {
                            putBoolean(SETTING_MODE, true)
                        }
                    )
                }
            )
        }
    }
}


@Composable
fun AddressApp(
    navController: NavHostController,
    route: String,
) {
    Scaffold(

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
            AddressNavigation(navController = navController, route = route)
        }
    }
}

