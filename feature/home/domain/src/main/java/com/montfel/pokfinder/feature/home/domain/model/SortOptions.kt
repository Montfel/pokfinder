package com.montfel.pokfinder.feature.home.domain.model

sealed interface SortOptions {
    data object SmallestNumber : SortOptions
    data object HighestNumber : SortOptions
    data object Alphabetical : SortOptions
    data object ReverseAlphabetical : SortOptions
}
