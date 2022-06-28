package com.montfel.pokedex.helper

import android.util.Log
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T : Any> requestWrapper(call: suspend () -> T): ApiResponse<T> {

    fun onRequestError(exception: Exception) {
        Log.e("error", "onRequestError", exception)
    }

    return try {
        val dataResult = call()
        ApiResponse.SuccessResult(dataResult)
    } catch (exception: Exception) {
        when (exception) {
            is HttpException -> {
                onRequestError(exception)
                ApiResponse.ServerError.HttpError(
                    exception.message(),
                    exception.code(),
                    exception.response()?.errorBody()?.toString() ?: exception.message()
                )
            }
            is SocketException -> ApiResponse.ServerError.NetworkError(exception.message ?: "")
            is UnknownHostException,
            is SocketTimeoutException -> ApiResponse.OfflineError
            is CancellationException -> ApiResponse.RequestCancelled
            else -> {
                onRequestError(exception)
                ApiResponse.ServerError.UnknownError(exception.message ?: "")
            }
        }
    }
}

inline fun <T : Any, U : Any> ApiResponse<T>.mapSuccess(
    transform: (T) -> U
): ApiResponse<U> {
    return when (this) {
        is ApiResponse.SuccessResult -> ApiResponse.SuccessResult(transform(this.data))
        ApiResponse.OfflineError -> ApiResponse.OfflineError
        ApiResponse.RequestCancelled -> ApiResponse.RequestCancelled
        is ApiResponse.ServerError.HttpError -> ApiResponse.ServerError.HttpError(
            this.errorMsg,
            this.code,
            this.body
        )
        is ApiResponse.ServerError.NetworkError -> ApiResponse.ServerError.NetworkError(
            this.errorMsg
        )
        is ApiResponse.ServerError.UnknownError -> ApiResponse.ServerError.UnknownError(
            this.errorMsg
        )
    }
}
