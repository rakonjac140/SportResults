package com.appcrafters.sportresults.base.functional

sealed class Either<out T> {
    data class Success<T>(val data: T) : Either<T>()
    data class Error(val exception: Exception) : Either<Nothing>()
}