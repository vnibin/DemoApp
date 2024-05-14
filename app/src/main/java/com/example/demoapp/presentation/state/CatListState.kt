package com.example.demoapp.presentation.state

import com.example.demoapp.domain.model.CatModel

data class CatListState(
    val catList: List<CatModel> ? = emptyList(),
    val errorMsg: String? = "",
    val isLoading: Boolean = false
)
