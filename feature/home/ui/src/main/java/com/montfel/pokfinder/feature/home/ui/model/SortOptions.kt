package com.montfel.pokfinder.feature.home.ui.model

import com.montfel.pokfinder.feature.home.domain.model.OrderType

sealed class SortOptions(val orderType: OrderType) {
    data object SmallestNumber : SortOptions(orderType = OrderType.Ascendant)
    data object HighestNumber : SortOptions(orderType = OrderType.Descendant)
    data object Alphabetical : SortOptions(orderType = OrderType.Ascendant)
    data object ReverseAlphabetical : SortOptions(orderType = OrderType.Descendant)
}

