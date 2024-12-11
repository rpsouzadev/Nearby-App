package com.rpsouza.nearbyapp.ui.components.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rpsouza.nearbyapp.data.model.category.NearbyCategory

@Composable
fun NearbyCategoryFilterList(
    modifier: Modifier = Modifier,
    categories: List<NearbyCategory>,
    onSelectedCategoryChanged: (category: NearbyCategory) -> Unit
) {
    var selectedCategoryId by remember { mutableStateOf(categories.firstOrNull()?.id.orEmpty()) }

    LaunchedEffect(key1 = selectedCategoryId) {
        val selectedCategoryOrNull = categories.find { it.id == selectedCategoryId }
        selectedCategoryOrNull?.let {
            onSelectedCategoryChanged(it)
        }
    }

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 34.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = categories, key = {it.id}) {category ->
            NearbyCategoryFilterChip(
                category = category,
                isSelected = category.id == selectedCategoryId,
                onClick = { isSelected ->
                    if (isSelected) {
                        selectedCategoryId = category.id
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun NearbyCategoryFilterListPreview() {
    NearbyCategoryFilterList(
        modifier = Modifier.fillMaxWidth(),
        categories = listOf(
            NearbyCategory(
                id = "1",
                name = "Alimentação"
            ),
            NearbyCategory(
                id = "2",
                name = "Supermercado"
            ),
            NearbyCategory(
                id = "3",
                name = "Farmácia"
            ),
            NearbyCategory(
                id = "4",
                name = "Padaria"
            )
        ),
        onSelectedCategoryChanged = {}
    )
}