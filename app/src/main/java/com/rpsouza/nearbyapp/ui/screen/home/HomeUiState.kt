package com.rpsouza.nearbyapp.ui.screen.home

import com.google.android.gms.maps.model.LatLng
import com.rpsouza.nearbyapp.data.model.category.Category
import com.rpsouza.nearbyapp.data.model.market.Market

data class HomeUiState(
    val categories: List<Category>? = null,
    val markets: List<Market>? = null,
    val marketLocations: List<LatLng>? = null
)