package com.montfel.pokedex.helper

sealed class ApiResponse<out T : Any> {

    class SuccessResult<out T : Any>(val data: T) : ApiResponse<T>()

    object OfflineError : ApiResponse<Nothing>()

    sealed class ServerError : ApiResponse<Nothing>() {
        abstract val errorMsg: String

        class HttpError(
            override val errorMsg: String,
            val code: Int,
            val body: String
        ) : ServerError()

        class NetworkError(override val errorMsg: String) : ServerError()

        class UnknownError(override val errorMsg: String) : ServerError()
    }

    object RequestCancelled : ApiResponse<Nothing>()
}
