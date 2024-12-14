package com.rpsouza.nearbyapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.rpsouza.nearbyapp.core.network.NearbyRemoteDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnFetchCategories -> fetchCategories()
            is HomeAction.OnFetchMarkets -> fetchMarkets(categoryId = action.categoryId)
        }
    }

    private fun fetchCategories() {
        viewModelScope.launch() {
            _uiState.update { currentState ->
                NearbyRemoteDataSource.getCategories().fold(
                    onSuccess = { categories ->
                        currentState.copy(categories = categories)
                    },
                    onFailure = { error ->
                        currentState.copy(categories = emptyList())
                    }
                )
            }
        }
    }

    private fun fetchMarkets(categoryId: String) {
        viewModelScope.launch() {
            _uiState.update { currentState ->
                NearbyRemoteDataSource.getMarkets(categoryId).fold(
                    onSuccess = { markets ->
                        currentState.copy(
                            markets = markets,
                            marketLocations = markets.map { market ->
                                LatLng(market.latitude, market.longitude)
                            }
                        )
                    },
                    onFailure = { error ->
                        currentState.copy(
                            markets = emptyList(),
                            marketLocations = emptyList()
                        )
                    }
                )
            }
        }
    }
}