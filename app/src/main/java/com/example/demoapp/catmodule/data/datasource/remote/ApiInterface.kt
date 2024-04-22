package com.example.demoapp.catmodule.data.datasource.remote

import com.example.demoapp.catmodule.data.model.CatResModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("v1/images/search?limit=10")
    suspend fun getAllCats() : Response<List<CatResModel>>

}