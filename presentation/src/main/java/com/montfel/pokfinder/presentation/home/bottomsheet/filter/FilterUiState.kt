package com.montfel.pokfinder.presentation.home.bottomsheet.filter

import com.montfel.pokfinder.designsystem.model.AssetFromType

data class FilterUiState(
    val selectedTypes: List<AssetFromType> = emptyList(),
    val selectedWeaknesses: List<AssetFromType> = emptyList(),
    val selectedHeights: List<AssetFromType> = emptyList(),
    val selectedWeights: List<AssetFromType> = emptyList(),
)
