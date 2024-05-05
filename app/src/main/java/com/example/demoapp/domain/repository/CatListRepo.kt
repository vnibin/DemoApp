package com.example.demoapp.domain.repository

import com.example.demoapp.domain.model.CatResModel
import retrofit2.Response

interface CatListRepo {

    suspend fun getCatData() : Response<List<CatResModel>>
}