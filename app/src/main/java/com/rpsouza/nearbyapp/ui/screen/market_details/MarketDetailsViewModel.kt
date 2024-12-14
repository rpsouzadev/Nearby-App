package com.rpsouza.nearbyapp.ui.screen.market_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.rpsouza.nearbyapp.core.network.NearbyRemoteDataSource
import com.rpsouza.nearbyapp.ui.screen.home.HomeAction
import com.rpsouza.nearbyapp.ui.screen.home.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MarketDetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MarketDetailsUiState())
    val uiState: StateFlow<MarketDetailsUiState> = _uiState.asStateFlow()

    fun onAction(action: MarketDetailsAction) {
        when (action) {
            is MarketDetailsAction.OnFetchRules -> fetchRules(marketId = action.marketId)
            is MarketDetailsAction.OnFetchCoupon -> fetchCoupon(qrCodeContent = action.qrCodeContent)
            is MarketDetailsAction.OnResetCoupon -> resetCoupon()
        }
    }

    private fun fetchRules(marketId: String) {
        viewModelScope.launch() {
            NearbyRemoteDataSource.getMarketDetails(marketId = marketId)
                .onSuccess { marketDetails ->
                    _uiState.update { currentState ->
                        currentState.copy(rules = marketDetails.rules)
                    }
                }
                .onFailure { error ->
                    _uiState.update { currentState ->
                        currentState.copy(rules = emptyList())
                    }
                }
        }
    }

    private fun fetchCoupon(qrCodeContent: String) {
        viewModelScope.launch() {
            NearbyRemoteDataSource.patchCoupon(marketId = qrCodeContent)
                .onSuccess { coupon ->
                    _uiState.update { currentState ->
                        currentState.copy(coupon = coupon.coupon)
                    }
                }
                .onFailure { error ->
                    _uiState.update { currentState ->
                        currentState.copy(coupon = "")
                    }
                }
        }
    }

    private fun resetCoupon() {
        _uiState.update { currentState ->
            currentState.copy(coupon = null)
        }
    }

}