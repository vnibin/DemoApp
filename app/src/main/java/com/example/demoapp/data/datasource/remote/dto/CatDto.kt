package com.example.demoapp.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class CatDto(
    @SerializedName("id") var id: String? = null,
    @SerializedName("url") var url: String="",
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null
)
