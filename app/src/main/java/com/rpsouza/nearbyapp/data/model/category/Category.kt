package com.rpsouza.nearbyapp.data.model.category

import androidx.annotation.DrawableRes
import com.rpsouza.nearbyapp.data.enum.NearbyCategoryFilterChipView
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: String,
    val name: String,
) {
    @get:DrawableRes
    val icon: Int?
        get() = NearbyCategoryFilterChipView.fromDescription(description = name)?.icon

}