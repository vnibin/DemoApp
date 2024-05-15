package com.example.demoapp.data.datasource.remote.mapper

import com.example.demoapp.data.datasource.remote.dto.CatDto
import com.example.demoapp.domain.model.CatModel


fun CatDto.toDomainCatModel() : CatModel {
    return CatModel(url)
}