package com.example.demoapp.catmodule.data.repository

import com.example.demoapp.catmodule.data.datasource.remote.ApiInterface
import com.example.demoapp.catmodule.data.model.CatResModel
import retrofit2.Response
import javax.inject.Inject

class CatListRepository @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getCatData() : Response<List<CatResModel>>
    {
        return apiInterface.getAllCats()
    }
}