package com.example.demoapp.domain.usecase

import com.example.demoapp.domain.repository.CatListRepo
import javax.inject.Inject

class CatListUseCase @Inject constructor(private val repository: CatListRepo)  {

    operator fun invoke() =repository.getCatData()
}