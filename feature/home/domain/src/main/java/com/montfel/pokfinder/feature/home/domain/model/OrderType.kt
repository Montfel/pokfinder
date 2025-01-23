package com.montfel.pokfinder.feature.home.domain.model

sealed interface OrderType {
    data object Ascendant : OrderType
    data object Descendant : OrderType
}
