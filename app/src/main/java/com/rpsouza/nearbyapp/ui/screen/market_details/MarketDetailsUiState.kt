package com.rpsouza.nearbyapp.ui.screen.market_details

import com.google.android.gms.maps.model.LatLng
import com.rpsouza.nearbyapp.data.model.category.Category
import com.rpsouza.nearbyapp.data.model.market.Market
import com.rpsouza.nearbyapp.data.model.market.Rule

data class MarketDetailsUiState(
    val rules: List<Rule>? = null,
    val coupon: String? = null
)