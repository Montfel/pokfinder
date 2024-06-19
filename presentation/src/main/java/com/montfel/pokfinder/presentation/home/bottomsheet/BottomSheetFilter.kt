package com.montfel.pokfinder.presentation.home.bottomsheet

sealed interface BottomSheetFilter {
    data object Generation : BottomSheetFilter
    data object Sort : BottomSheetFilter
    data object Filter : BottomSheetFilter
}
