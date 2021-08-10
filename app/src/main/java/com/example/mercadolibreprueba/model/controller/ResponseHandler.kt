package com.example.mercadolibreprueba.model.controller

import android.content.Context
import com.example.mercadolibreprueba.R
import retrofit2.HttpException
import java.net.SocketTimeoutException

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

open class ResponseHandler(private val appContext: Context) {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> Resource.error(getErrorMessage(ErrorCodes.SocketTimeOut.code), null)
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> appContext.getString(R.string.timeout)
            401 -> appContext.getString(R.string.unautorized)
            404 -> appContext.getString(R.string.notfound)
            else -> appContext.getString(R.string.genericerror)
        }
    }
}