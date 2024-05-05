package com.example.demoapp.core.network

import com.example.demoapp.domain.model.CatResModel
import com.example.demoapp.core.common.Status
import com.google.gson.JsonObject
import retrofit2.Response

abstract class NetworkUseCase {

    abstract val isAvailInternet: Boolean

    //If we have encrypted String format,we can make common handler for all the api's.
    //as of now single api is used so it will take response of that type
    abstract fun response200(response: Any, status: Status): ResponseApi

    abstract fun response401(apiTypestatus: Status): ResponseApi

    abstract fun responseFail400( status: Status): ResponseApi

    abstract fun responseFail(status: Status): ResponseApi
}
