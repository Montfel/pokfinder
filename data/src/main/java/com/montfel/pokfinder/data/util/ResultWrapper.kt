package com.montfel.pokfinder.data.util

import com.montfel.pokfinder.domain.util.ErrorType
import com.montfel.pokfinder.domain.util.ResultType
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

        when (exception) {
            is HttpException -> ResultType.Failure(ErrorType.Http)
            is SocketException -> ResultType.Failure(ErrorType.Network)
            is UnknownHostException, is SocketTimeoutException -> ResultType.Failure(ErrorType.Offline)
            is CancellationException -> throw exception
            else -> ResultType.Failure(ErrorType.Unknown)
        }
    }
}
