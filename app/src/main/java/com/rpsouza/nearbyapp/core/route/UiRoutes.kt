package com.rpsouza.nearbyapp.core.route

import com.rpsouza.nearbyapp.data.model.market.Market
import kotlinx.serialization.Serializable

sealed class AppRoutes {

    @Serializable
    data object Splash : AppRoutes()

    @Serializable
    data object Welcome : AppRoutes()

    @Serializable
    data object Home : AppRoutes()

    @Serializable
    data object QRCodeScanner : AppRoutes()
}
