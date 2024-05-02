package com.example.demoapp.data.repository

import com.example.demoapp.data.datasource.remote.ApiInterface
import com.example.demoapp.domain.model.CatResModel
import com.example.demoapp.domain.repository.CatListRepo
import retrofit2.Response
import javax.inject.Inject


class CatListRepoImpl @Inject constructor(val apiInterface: ApiInterface) : CatListRepo {
    override suspend fun getCatData(): Response<List<CatResModel>> {
        return apiInterface.getAllCats(10)

    }
}