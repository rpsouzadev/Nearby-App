package com.rpsouza.nearbyapp.ui.screen.market_details

import com.rpsouza.nearbyapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rpsouza.nearbyapp.data.model.market.Market
import com.rpsouza.nearbyapp.data.model.mock.mockMarkets
import com.rpsouza.nearbyapp.ui.components.button.NearbyButton
import com.rpsouza.nearbyapp.ui.components.market_details.NearbyMarketDetailsCoupons
import com.rpsouza.nearbyapp.ui.components.market_details.NearbyMarketDetailsInfos
import com.rpsouza.nearbyapp.ui.components.market_details.NearbyMarketDetailsRules
import com.rpsouza.nearbyapp.ui.theme.Typography

@Composable
fun MarketDetailsScreen(
    market: Market,
    uiState: MarketDetailsUiState,
    onAction: (MarketDetailsAction) -> Unit,
    onNavigateToQrCodeScanner: () -> Unit,
    onNavigateBack: () -> Unit
) {
    LaunchedEffect(true) {
        onAction(MarketDetailsAction.OnFetchRules(marketId = market.id))
    }

    MarketDetailsContentScreen(
        market = market,
        uiState = uiState,
        onAction = onAction,
        onNavigateToQrCodeScanner = onNavigateToQrCodeScanner,
        onNavigateBack = onNavigateBack
    )
}

@Composable
private fun MarketDetailsContentScreen(
    market: Market,
    uiState: MarketDetailsUiState,
    onAction: (MarketDetailsAction) -> Unit,
    onNavigateToQrCodeScanner: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentDescription = "Imagem do Local",
            contentScale = ContentScale.Crop,
            model = market.cover
        )

        NearbyButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(24.dp),
            iconRes = R.drawable.ic_arrow_left,
            onClick = onNavigateBack
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(36.dp)
            ) {
                Column (
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = market.name,
                        style = Typography.headlineLarge
                    )

                    Text(
                        text = market.description,
                        style = Typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())

                ) {
                    NearbyMarketDetailsInfos(market = market)

                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )

                    if (!uiState.rules.isNullOrEmpty()) {
                        NearbyMarketDetailsRules(rules = uiState.rules)

                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                        )
                    }

                    if (!uiState.coupon.isNullOrEmpty()) {
                        NearbyMarketDetailsCoupons(coupons = listOf(uiState.coupon))
                    }
                }

                NearbyButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    text = "Ler QR Code",
                    onClick = onNavigateToQrCodeScanner
                )
            }
        }
    }
}

@Preview
@Composable
private fun MarketDetailsScreenPreview() {
    MarketDetailsContentScreen(
        market = mockMarkets.first(),
        uiState = MarketDetailsUiState(),
        onAction = {},
        onNavigateToQrCodeScanner = {},
        onNavigateBack = {}
    )
}