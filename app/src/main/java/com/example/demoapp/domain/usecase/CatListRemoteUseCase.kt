package com.example.demoapp.domain.usecase

import com.example.demoapp.domain.model.CatResModel
import com.example.demoapp.domain.repository.CatListRepo
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

    //If we have encrypted String format,we can make common handler for all the api's.
    //as of now single api is used so it will take response of that type
    override fun response200(response: Any, status: Status): ResponseApi {

        //we can check the api type
        if (status == Status.CATDETAILS)  {
            val r=response as Response<*>
            val res =r.body()
            return ResponseApi.success(res as List<*>, status)
        }
        else
            return ResponseApi.fail(null, status)

    }

    override fun response401(apiTypestatus: Status): ResponseApi {
        return ResponseApi.authFail( apiTypestatus)

    }

    override fun responseFail400( status: Status): ResponseApi {
        return ResponseApi.fail400("Bad Request", status)

    }

    override fun responseFail(status: Status): ResponseApi {
        return ResponseApi.fail(null, status)
    }


    suspend fun getCatData() : ResponseApi {
        val response  =  repository.getCatData()
        var responseApi: ResponseApi?=null

        if(response.isSuccessful && response.body()!=null)
        {
          responseApi= handleResponse(response, Status.CATDETAILS)

        }
        else
        {
           responseApi= responseFail(Status.CATDETAILS )
        }
        return responseApi
    }

    private fun handleResponse(response: Response<List<CatResModel>>, apiTypestatus: Status): ResponseApi {
        return when (response.code()) {
            RES_200 -> response200(response, apiTypestatus)
            RES_401 -> response401(apiTypestatus)
            RES_400 -> responseFail400(apiTypestatus)
            RES_FAIL -> responseFail(apiTypestatus)
            else -> responseFail(apiTypestatus)
        }
    }




}