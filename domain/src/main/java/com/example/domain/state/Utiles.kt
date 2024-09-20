package com.example.domain.state

import com.example.domain.entity.BaseResponse
import com.example.domain.entity.convertErrorBody
import com.example.domain.entity.handleError
import kotlinx.coroutines.TimeoutCancellationException
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException


fun <T> DataState<T>.applyCommonSideEffects(
        networkExtensionsActions: NetworkExtensionsActions,
        showLoading: Boolean = true,
        showSuccessToast: Boolean = false,
        cancelNotActive: Boolean = false,
        onSuccess: (T) -> Unit = {},
    ) {

        when (this) {
            is DataState.Loading -> {
                if (showLoading) networkExtensionsActions.onLoad(true)
            }

            is DataState.Success -> {
                networkExtensionsActions.onLoad(false)
                val baseResponse = data as BaseResponse<*>
                if (baseResponse.results != null) {
                    if (showSuccessToast) networkExtensionsActions.onShowSuccessToast("Operation successful")
                    onSuccess(this.data)
                } else {
                    networkExtensionsActions.onFail("Operation failed")
                }
            }

            is DataState.Error -> {
                networkExtensionsActions.onLoad(false)
                handleErrorForException<Exception>( exception)
            }

            DataState.Idle -> {
                networkExtensionsActions.onLoad(false)
            }
        }
    }

fun <T> handleErrorForException(it: Exception): DataState<T> {
    it.printStackTrace()
    return when (it) {
        is TimeoutCancellationException -> {
            DataState.Error(Exception("Timeout"))
        }

        is UnknownHostException -> {
            DataState.Error(Exception("No Internet"))
        }

        is IOException -> {
            DataState.Error(Exception("Network Error"))
        }

        is HttpException -> {
            DataState.Error(convertErrorBody(it))
        }

        else -> {
            DataState.Error(Exception("Unknown Error"))
        }
    }

}
