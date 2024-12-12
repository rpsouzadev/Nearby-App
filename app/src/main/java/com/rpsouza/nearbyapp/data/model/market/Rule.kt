package com.rpsouza.nearbyapp.data.model.market

import kotlinx.serialization.Serializable

@Serializable
data class Rule(
    val id: String,
    val description: String,
    val marketId: String
)
