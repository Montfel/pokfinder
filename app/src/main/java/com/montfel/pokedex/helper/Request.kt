package com.montfel.pokedex.helper

import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class Response<out T : Any> {

    class Success<out T : Any>(val data: T) : Response<T>()

    object OfflineError : Response<Nothing>()

    sealed class ServerError : Response<Nothing>() {
        abstract val errorMsg: String

        class HttpError(
            override val errorMsg: String,
            val code: Int,
            val body: String
        ) : ServerError()

        class NetworkError(override val errorMsg: String) : ServerError()

        class UnknownError(override val errorMsg: String) : ServerError()
    }

    object RequestCancelled : Response<Nothing>()
}

suspend fun <T : Any> requestWrapper(call: suspend () -> T): Response<T> {

    return try {
        Response.Success(call())
    } catch (exception: Exception) {
        when (exception) {
            is HttpException -> {
                Response.ServerError.HttpError(
                    errorMsg = exception.message(),
                    code = exception.code(),
                    body = exception.response()?.errorBody()?.toString() ?: exception.message()
                )
            }
            is SocketException -> Response.ServerError.NetworkError(exception.message ?: "")
            is UnknownHostException, is SocketTimeoutException -> Response.OfflineError
            is CancellationException -> Response.RequestCancelled
            else -> {
                Response.ServerError.UnknownError(errorMsg = exception.message ?: "")
            }
        }
    }
}

inline fun <T : Any, U : Any> Response<T>.mapSuccess(
    transform: (T) -> U
): Response<U> {
    return when (this) {
        is Response.Success -> Response.Success(transform(this.data))
        Response.OfflineError -> Response.OfflineError
        Response.RequestCancelled -> Response.RequestCancelled
        is Response.ServerError.HttpError -> Response.ServerError.HttpError(
            errorMsg = this.errorMsg,
            code = this.code,
            body = this.body
        )
        is Response.ServerError.NetworkError -> Response.ServerError.NetworkError(this.errorMsg)
        is Response.ServerError.UnknownError -> Response.ServerError.UnknownError(this.errorMsg)
    }
}
