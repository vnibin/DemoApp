package com.example.demoapp.data.datasource.remote.api

import com.example.demoapp.core.common.ApiConstants
import com.example.demoapp.core.common.AppConstants
import com.example.demoapp.data.datasource.remote.dto.CatDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(ApiConstants.GET_CATS)
    suspend fun getAllCats(@Query(AppConstants.LIMIT)limit:Int) : List<CatDto>

}