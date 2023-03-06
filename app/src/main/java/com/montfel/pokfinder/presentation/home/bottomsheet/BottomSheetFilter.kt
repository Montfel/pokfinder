package com.montfel.pokfinder.presentation.home.bottomsheet

sealed interface BottomSheetFilter {
    object Generation : BottomSheetFilter
    object Sort : BottomSheetFilter
    object Filter : BottomSheetFilter
}
