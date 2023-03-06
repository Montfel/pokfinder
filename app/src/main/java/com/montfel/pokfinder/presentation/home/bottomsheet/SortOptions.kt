package com.montfel.pokfinder.presentation.home.bottomsheet

sealed interface SortOptions {
    object SmallestNumber : SortOptions
    object HighestNumber : SortOptions
    object Alphabetical : SortOptions
    object ReverseAlphabetical : SortOptions
}
