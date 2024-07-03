package com.montfel.pokfinder.core.common.domain.util

sealed interface ErrorType {
    data object Offline : ErrorType
    data object Http : ErrorType
    data object Network : ErrorType
    data object Unknown : ErrorType
}
