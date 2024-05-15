package com.example.demoapp


import com.example.demoapp.core.common.AppConstants
import com.example.demoapp.core.common.Helper
import com.example.demoapp.data.datasource.remote.api.ApiInterface
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatListApiTest (){

    lateinit var mockWebServer: MockWebServer
    lateinit var apiInterface: ApiInterface




    @Before
    fun setUp()
    {
        mockWebServer= MockWebServer()
        apiInterface= Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
    }

    @Test
    fun testGetCatList()=runTest {
        val mockResponse=MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response =apiInterface.getAllCats(10)
        mockWebServer.takeRequest()

        Assert.assertEquals(true,response.isEmpty())


    }

    @Test
    fun getActualCatList()=runTest {
        val mockResponse=MockResponse()
        val content= Helper.readFileResource(AppConstants.JSON_PATH)
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response =apiInterface.getAllCats(10)
        mockWebServer.takeRequest()

        Assert.assertEquals(10, response.size)

    }

    @After
    fun tearDown()
    {
        mockWebServer.shutdown()

    }
}