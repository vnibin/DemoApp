package com.example.demoapp.core.network

import com.example.demoapp.core.common.Status

class ResponseApi
(

    val status: Status,

    val data: Any?,

    val apiTypeStatus: Status?) {

    companion object {


        fun loading(apiTypeStatus: Status?): ResponseApi {
            return ResponseApi(Status.LOADING, null, apiTypeStatus)
        }


        fun success(data: Any, apiTypeStatus: Status?): ResponseApi {
            return ResponseApi(Status.SUCCESS, data, apiTypeStatus)
        }



        fun fail400(data: Any, apiTypeStatus: Status?): ResponseApi {
            return ResponseApi(Status.FAIL_400,null, apiTypeStatus)
        }


        fun authFail(apiTypestatus: Status?): ResponseApi {
            return ResponseApi(Status.AUTH_FAIL, null, apiTypestatus)
        }


        fun fail(data: Any?, apiTypeStatus: Status?): ResponseApi {
            return ResponseApi(Status.FAIL, data, apiTypeStatus)
        }



    }

}