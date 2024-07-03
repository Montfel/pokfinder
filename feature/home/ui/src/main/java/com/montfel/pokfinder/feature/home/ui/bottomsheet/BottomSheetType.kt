package com.montfel.pokfinder.feature.home.ui.bottomsheet

sealed interface BottomSheetType {
    data object Generation : BottomSheetType
    data object Sort : BottomSheetType
    data object Filter : BottomSheetType
}
