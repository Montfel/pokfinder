package com.montfel.pokfinder.presentation.home.bottomsheet.filter

import com.montfel.pokfinder.core.common.domain.model.Type

data class FilterUiState(
    val selectedTypes: List<Type> = emptyList(),
)
