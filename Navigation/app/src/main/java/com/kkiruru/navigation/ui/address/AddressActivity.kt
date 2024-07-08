package com.kkiruru.navigation.ui.address

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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


class AddressActivity : ComponentActivity() {

    private val viewModel by viewModels<AddressViewModel>()

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
            AddressDestinations.SEARCH_ROUTE
        }

        viewModel.initSearchMode(isSearchOnly)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddressApp(
                        route = startDestination,
                        viewModel = viewModel
                    )
                }
            }
        }
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
    route: String,
    viewModel: AddressViewModel,
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
            AddressNavigation(
                startDestination = route,
                viewModel = viewModel
            )
        }
    }
}

