package com.example.demoapp.domain.repository

import com.example.demoapp.core.common.Resource
import com.example.demoapp.domain.model.CatModel
import kotlinx.coroutines.flow.Flow

interface CatListRepo {

     fun getCatData() : Flow<Resource<List<CatModel>>>
}


