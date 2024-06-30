package com.kkiruru.navigation.ui.address

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun SearchScreen(
    gotoSettings: () -> Unit,
    isSearchOnly: Boolean,
) {

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "SearchScreen  ${isSearchOnly}")

            if (isSearchOnly.not()) {
                Button(onClick = {
                    gotoSettings()
                }) {
                    Text(text = "gotoSettings")
                }
            }
        }
    }
}
