package com.rpsouza.nearbyapp.ui.screen.market_details

sealed class MarketDetailsAction {
    data class OnFetchRules(val marketId: String) : MarketDetailsAction()
    data class OnFetchCoupon(val qrCodeContent: String) : MarketDetailsAction()
    data object OnResetCoupon : MarketDetailsAction()
}