package com.example.demoapp.core.network

import com.example.demoapp.catmodule.data.model.CatResModel
import com.example.demoapp.core.common.Status
import com.google.gson.JsonObject
import retrofit2.Response

abstract class NetworkUseCase {

    abstract val isAvailInternet: Boolean

    abstract fun response200(response: Response<List<CatResModel>>, status: Status): ResponseApi

    abstract fun response401(apiTypestatus: Status): ResponseApi

    abstract fun responseFail400( status: Status): ResponseApi

    abstract fun responseFail(status: Status): ResponseApi
}
