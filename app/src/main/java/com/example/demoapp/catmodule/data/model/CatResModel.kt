package com.example.demoapp.catmodule.data.model

import com.google.gson.annotations.SerializedName

data class CatResModel(

    @SerializedName("id") var id: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null
)
