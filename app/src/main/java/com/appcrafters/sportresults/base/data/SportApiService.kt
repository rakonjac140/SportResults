package com.appcrafters.sportresults.base.data

import com.appcrafters.sportresults.base.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path


interface SportApiService {

    @GET("sports")
    fun getSport(@Header("X-RapidAPI-Key") key: String, @Header("X-RapidAPI-Host") host: String): Call<Sports>

    @GET("sports/{id}/events/live")
    fun getEventsBySportId(@Header("X-RapidAPI-Key") key: String, @Header("X-RapidAPI-Host") host: String, @Path("id") id: Int): Call<Events>

    @GET("events/{id}")
    fun getEventById(@Header("X-RapidAPI-Key") key: String, @Header("X-RapidAPI-Host") host: String, @Path("id") id: Int): Call<EventData>

}