package com.montfel.pokfinder.domain.home.model

sealed interface SortOptions {
    object SmallestNumber : SortOptions
    object HighestNumber : SortOptions
    object Alphabetical : SortOptions
    object ReverseAlphabetical : SortOptions
}
