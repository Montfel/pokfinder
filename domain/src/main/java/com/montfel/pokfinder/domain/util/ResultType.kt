package com.montfel.pokfinder.domain.util

sealed interface ResultType<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultType<T>
    data class Failure(val errorType: ErrorType) : ResultType<Nothing>
}
