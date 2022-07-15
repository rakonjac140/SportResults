package com.appcrafters.sportresults.base.data

object ApiServiceProvider {
    val sportApiService = RetrofitBuilder.retrofit.create(SportApiService::class.java)
}