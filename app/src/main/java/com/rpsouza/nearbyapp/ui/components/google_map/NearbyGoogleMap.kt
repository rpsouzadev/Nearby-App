package com.rpsouza.nearbyapp.ui.components.google_map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.rpsouza.nearbyapp.R
import com.rpsouza.nearbyapp.data.model.mock.mockUserLocation
import com.rpsouza.nearbyapp.ui.screen.home.HomeUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList

@Composable
fun NearbyGoogleMap(
    uiState: HomeUiState,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(mockUserLocation, 13f)
    }
    val uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = uiSettings
    ) {
        context.getDrawable(R.drawable.ic_user_location)?.let { drawable ->
            Marker(
                state = MarkerState(position = mockUserLocation),
                icon = BitmapDescriptorFactory.fromBitmap(
                    drawable.toBitmap(
                        width = density.run { 72.dp.toPx().toInt() },
                        height = density.run { 72.dp.toPx().toInt() }
                    )
                )
            )
        }

        if (!uiState.markets.isNullOrEmpty()) {
            uiState.marketLocations?.toImmutableList()
                ?.forEachIndexed { index, location ->
                    context.getDrawable(R.drawable.img_pin)?.let { drawable ->
                        Marker(
                            state = MarkerState(position = location),
                            icon = BitmapDescriptorFactory.fromBitmap(
                                drawable.toBitmap(
                                    width = density.run { 36.dp.toPx().toInt() },
                                    height = density.run { 36.dp.toPx().toInt() }
                                )
                            ),
                            title = uiState.markets[index].name
                        )
                    }.also {
                        coroutineScope.launch {
                            val allMarks = uiState.marketLocations.plus(
                                mockUserLocation
                            )

                            val southWestPoint =
                                allMarks.minByOrNull { it.latitude } ?: LatLng(
                                    0.0,
                                    0.0
                                )
                            val northEastPoint =
                                allMarks.maxByOrNull { it.longitude } ?: LatLng(
                                    0.0,
                                    0.0
                                )
                            val cameraUpdate =
                                CameraUpdateFactory.newCameraPosition(
                                    CameraPosition(
                                        LatLng(
                                            (southWestPoint.latitude + northEastPoint.latitude) / 2,
                                            (southWestPoint.longitude + northEastPoint.longitude) / 2
                                        ),
                                        13f,
                                        0f,
                                        0f
                                    )
                                )
                            delay(200)
                            cameraPositionState.animate(
                                cameraUpdate,
                                durationMs = 500
                            )
                        }
                    }
                }
        }
    }
}