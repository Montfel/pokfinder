package com.montfel.pokfinder.domain.home.model

sealed interface SortOptions {
    data object SmallestNumber : SortOptions
    data object HighestNumber : SortOptions
    data object Alphabetical : SortOptions
    data object ReverseAlphabetical : SortOptions
}
