package com.example.demoapp.catmodule.data.repository

import com.example.demoapp.catmodule.data.model.CatResModel
import retrofit2.Response

interface CatListRepo {

    suspend fun getCatData() : Response<List<CatResModel>>
}