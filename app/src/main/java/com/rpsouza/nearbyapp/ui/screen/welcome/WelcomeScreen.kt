package com.rpsouza.nearbyapp.ui.screen.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rpsouza.nearbyapp.ui.components.button.NearbyButton
import com.rpsouza.nearbyapp.ui.components.welcome.WelcomeContent
import com.rpsouza.nearbyapp.ui.components.welcome.WelcomeHeader

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    WelcomeScreenContent(modifier = modifier)
}

@Composable
private fun WelcomeScreenContent(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(horizontal = 40.dp, vertical = 48.dp)
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        WelcomeHeader()
        WelcomeContent()

        NearbyButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Começar",
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreenContent()
}