package com.rpsouza.nearbyapp.ui.screen.home

sealed class HomeAction {
    data object OnFetchCategories : HomeAction()
    data class OnFetchMarkets(val categoryId: String) : HomeAction()
}