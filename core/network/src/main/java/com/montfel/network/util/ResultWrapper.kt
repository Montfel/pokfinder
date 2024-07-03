package com.montfel.network.util

import com.montfel.pokfinder.core.common.domain.util.ErrorType
import com.montfel.pokfinder.core.common.domain.util.ResultType
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException
import retrofit2.HttpException

suspend fun <T : Any> resultWrapper(call: suspend () -> T): ResultType<T> {
    return runCatching {
        ResultType.Success(call())
    }.getOrElse { exception ->
        exception.printStackTrace()

        ResultType.Failure(getErrorTypeFromThrowable(exception))
    }
}

fun getErrorTypeFromThrowable(it: Throwable) = when (it) {
    is HttpException -> {
        ErrorType.Http
    }

    is SocketException -> {
        ErrorType.Network
    }

    is UnknownHostException, is SocketTimeoutException -> {
        ErrorType.Offline
    }

    is CancellationException -> {
        throw it
    }

    else -> {
        ErrorType.Unknown
    }
}
