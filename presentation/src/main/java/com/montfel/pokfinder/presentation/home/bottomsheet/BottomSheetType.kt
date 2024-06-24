package com.montfel.pokfinder.presentation.home.bottomsheet

sealed interface BottomSheetType {
    data object Generation : BottomSheetType
    data object Sort : BottomSheetType
    data object Filter : BottomSheetType
}
