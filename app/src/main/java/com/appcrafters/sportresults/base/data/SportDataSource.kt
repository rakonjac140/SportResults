package com.appcrafters.sportresults.base.data

import com.appcrafters.sportresults.base.functional.Either
import com.appcrafters.sportresults.base.model.EventData
import com.appcrafters.sportresults.base.model.Events
import com.appcrafters.sportresults.base.model.Sports
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call


interface ISportsDataSource{
    suspend fun getSport(): Either<Sports>
    suspend fun getEventsBySportId(id: Int): Either<Events>
}

class SportDataSource(private val apiService: SportApiService):ISportsDataSource {

    companion object{
        private const val KEY = "8f426cb4a2msh696dcc73a2b6428p16eeb1jsne26b07c2b5fb"
        private const val HOST = "sportscore1.p.rapidapi.com"
    }

    override suspend fun getSport(): Either<Sports> = handleCall(apiService.getSport(KEY,HOST))

    override suspend fun getEventsBySportId(id: Int): Either<Events> = handleCall(apiService.getEventsBySportId(KEY, HOST,id))

    private suspend fun <T> handleCall(call: Call<T>): Either<T>{
        return withContext(Dispatchers.IO) {
            val response = call.execute()
            if(response.isSuccessful){
                Either.Success(response.body()!!)
            }else{
                Either.Error(Exception(response.message()))
            }
        }
    }

    suspend fun getEventById(id: Int): Either<EventData> = handleCall(apiService.getEventById(KEY, HOST,id))

}