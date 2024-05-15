package com.example.demoapp

import com.example.demoapp.core.common.AppConstants
import com.example.demoapp.core.common.Helper
import com.example.demoapp.core.common.Resource
import com.example.demoapp.domain.model.CatModel
import com.example.demoapp.domain.repository.CatListRepo
import com.example.demoapp.domain.usecase.CatListUseCase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


class CatListUseCaseTest {

    private val repository: CatListRepo = mock(CatListRepo::class.java)
    private val CatListUseCase = CatListUseCase(repository)


    @Test
    fun getUseCaseData() {

        val jsonString= Helper.readFileResource(AppConstants.JSON_PATH)
        val gson = Gson()
        val listType = object : TypeToken<ArrayList<CatModel>>() {}.type
       val list= gson.fromJson(jsonString, listType) as List<CatModel>

     val expectedData= flow<Resource<List<CatModel>>> {
         emit(Resource.Success(list))
     }
        `when`(repository.getCatData()).thenReturn(expectedData)
        val result = CatListUseCase.invoke()
        assertEquals(expectedData, result)
    }



}