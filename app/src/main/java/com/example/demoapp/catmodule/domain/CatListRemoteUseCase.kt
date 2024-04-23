package com.example.demoapp.catmodule.domain

import com.example.demoapp.catmodule.data.model.CatResModel
import com.example.demoapp.catmodule.data.repository.CatListRepo
import com.example.demoapp.core.common.AppConstants.Companion.RES_200
import com.example.demoapp.core.common.AppConstants.Companion.RES_400
import com.example.demoapp.core.common.AppConstants.Companion.RES_401
import com.example.demoapp.core.common.AppConstants.Companion.RES_FAIL
import com.example.demoapp.core.common.Status
import com.example.demoapp.core.network.NetworkUseCase
import com.example.demoapp.core.network.ResponseApi
import retrofit2.Response
import javax.inject.Inject

class CatListRemoteUseCase @Inject constructor(private val repository: CatListRepo)  : NetworkUseCase() {


    override val isAvailInternet: Boolean
        get() = TODO("Not yet implemented")

    override fun response200(response: Response<List<CatResModel>>, status: Status): ResponseApi {

        if (status == Status.CATDETAILS) {
            val res =response.body()
            return ResponseApi.success(res as List<CatResModel>, status)
        }
        else
            return ResponseApi.fail("Something Went Wrong Please try again", status)

    }

    override fun response401(apiTypestatus: Status): ResponseApi {
        return ResponseApi.fail("Something Went Wrong Please try again", apiTypestatus)

    }

    override fun responseFail400( status: Status): ResponseApi {
        return ResponseApi.fail("Something Went Wrong Please try again", status)

    }

    override fun responseFail(status: Status): ResponseApi {
        return ResponseApi.fail("Something Went Wrong Please try again", status)
    }


    suspend fun getCatData() : ResponseApi {
        val response : Response<List<CatResModel>> =  repository.getCatData()
        var responseApi:ResponseApi?=null

        if(response.isSuccessful)
        {
          responseApi= handleResponse(response,Status.CATDETAILS)

        }
        else
        {
           responseApi= responseFail(Status.CATDETAILS )
        }
        return responseApi
    }

    private fun handleResponse(response: Response<List<CatResModel>>, apiTypestatus: Status): ResponseApi{
        return when (response.code()) {
            RES_200 -> response200(response, apiTypestatus)
            RES_401 -> response401(apiTypestatus)
            RES_400 -> responseFail400(apiTypestatus)
            RES_FAIL -> responseFail(apiTypestatus)
            else -> responseFail(apiTypestatus)
        }
    }




}