package com.example.demoapp.data.datasource.remote.repository

import com.example.demoapp.core.common.Resource
import com.example.demoapp.data.datasource.remote.api.ApiInterface
import com.example.demoapp.data.datasource.remote.mapper.toDomainCatModel
import com.example.demoapp.domain.model.CatModel
import com.example.demoapp.domain.repository.CatListRepo
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ViewModelScoped
class CatListRepoImpl @Inject constructor(val apiInterface: ApiInterface) : CatListRepo {
    override  fun getCatData(): Flow<Resource<List<CatModel>>> = flow {
        emit(Resource.Loading())
        val result = apiInterface.getAllCats(10).map {
            it.toDomainCatModel()
        }
        emit(Resource.Success(result))

    }.flowOn(Dispatchers.IO)
        .catch {
            emit(Resource.Error(it.message.toString()))
        }

}