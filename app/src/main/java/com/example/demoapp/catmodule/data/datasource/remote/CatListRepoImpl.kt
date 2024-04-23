package com.example.demoapp.catmodule.data.datasource.remote

import com.example.demoapp.catmodule.data.model.CatResModel
import com.example.demoapp.catmodule.data.repository.CatListRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


class CatListRepoImpl @Inject constructor(val apiInterface: ApiInterface) : CatListRepo {
    override suspend fun getCatData(): Response<List<CatResModel>> {
        return apiInterface.getAllCats()

    }
}