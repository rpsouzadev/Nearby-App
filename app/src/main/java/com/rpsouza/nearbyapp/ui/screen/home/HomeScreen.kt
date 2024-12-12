package com.rpsouza.nearbyapp.ui.screen.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.GoogleMap
import com.rpsouza.nearbyapp.data.model.market.Market
import com.rpsouza.nearbyapp.data.model.mock.mockCategories
import com.rpsouza.nearbyapp.data.model.mock.mockMarkets
import com.rpsouza.nearbyapp.ui.components.category.NearbyCategoryFilterList
import com.rpsouza.nearbyapp.ui.components.market.NearbyMarketCardList
import com.rpsouza.nearbyapp.ui.theme.Gray100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateToMarketDetails: (Market) -> Unit) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    var isBottomSheetOpened by remember { mutableStateOf(true) }
    val configuration = LocalConfiguration.current

    HomeScreenContent(
        bottomSheetState = bottomSheetState,
        isBottomSheetOpened = isBottomSheetOpened,
        configuration = configuration,
        onNavigateToMarketDetails = { marketItem ->
            onNavigateToMarketDetails(marketItem)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenContent(
    bottomSheetState: BottomSheetScaffoldState,
    isBottomSheetOpened: Boolean,
    configuration: Configuration,
    onNavigateToMarketDetails: (Market) -> Unit
) {
    if (isBottomSheetOpened) {
        BottomSheetScaffold(
            scaffoldState = bottomSheetState,
            sheetContentColor = Gray100,
            sheetPeekHeight = configuration.screenWidthDp.dp,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetContent = {
                NearbyMarketCardList(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    markets = mockMarkets,
                    onMarketClick = { marketItem ->
                        onNavigateToMarketDetails(marketItem)
                    }
                )
            },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                ) {
                    GoogleMap(
                        modifier = Modifier
                            .fillMaxSize()
                    )

                    NearbyCategoryFilterList(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                            .align(Alignment.TopStart),
                        categories = mockCategories,
                        onSelectedCategoryChanged = {}
                    )
                }
            }
        )
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        onNavigateToMarketDetails = {}
    )
}