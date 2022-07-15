package com.appcrafters.sportresults.base.data

import com.appcrafters.sportresults.base.functional.Either
import com.appcrafters.sportresults.base.model.Sports
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.MockitoAnnotations.openMocks
import retrofit2.Call
import retrofit2.Response


class SportDataSourceTest {

    @Mock
    lateinit var apiService: SportApiService
    @Mock
    lateinit var getSportCall: Call<Sports>
    lateinit var dataSource: SportDataSource

    @Before
    fun setUp(){
        openMocks(this)
        dataSource = SportDataSource(apiService)
    }

    @Test
   fun `test getSport, has response, Success returned`() = runBlocking {

        val expectedSports = Sports(listOf())
        val expectedResult = Either.Success(expectedSports)

        `when`(apiService.getSport(anyString(), anyString())).thenReturn(getSportCall)
        `when`(getSportCall.execute()).thenReturn(Response.success(expectedSports))

        val result = dataSource.getSport()

        assertEquals(expectedResult, result)
    }

    @Test
    fun `test getSport, has error, Error returned`() = runBlocking {

        val expectedResponseBody = ResponseBody.create(
            MediaType.parse("application/json"), ""
        )
        `when`(apiService.getSport(anyString(), anyString())).thenReturn(getSportCall)
        `when`(getSportCall.execute()).thenReturn(Response.error(400,expectedResponseBody))

        val result = dataSource.getSport()
        assertTrue(result is Either.Error)
    }
}