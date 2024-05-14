package com.example.demoapp.data.datasource.remote

import com.example.demoapp.data.datasource.remote.dto.CatDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("v1/images/search")
    suspend fun getAllCats(@Query("limit")limit:Int) : List<CatDto>

}