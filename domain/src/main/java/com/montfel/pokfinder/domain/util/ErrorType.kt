package com.montfel.pokfinder.domain.util

sealed interface ErrorType {
    data object Offline : ErrorType
    data object Http : ErrorType
    data object Network : ErrorType
    data object Unknown : ErrorType
}
