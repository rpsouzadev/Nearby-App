package com.rpsouza.nearbyapp.ui.components.market

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rpsouza.nearbyapp.data.model.market.NearbyMarket
import com.rpsouza.nearbyapp.data.model.mock.mockMarkets
import com.rpsouza.nearbyapp.ui.theme.Typography


@Composable
fun NearbyMarketCardList(
    modifier: Modifier = Modifier,
    markets: List<NearbyMarket>,
    onMarketClick: (NearbyMarket) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(text = "Explore locais perto de você", style = Typography.bodyLarge)
        }
        items(items = markets, key = { it.id }) { market ->
            NearbyMarketCard(
                market = market,
                onClick = {
                    onMarketClick(market)
                }
            )
        }
    }
}

@Preview
@Composable
private fun NearbyMarketCardListPreview() {
    NearbyMarketCardList(
        markets = mockMarkets,
        onMarketClick = {}
    )
}