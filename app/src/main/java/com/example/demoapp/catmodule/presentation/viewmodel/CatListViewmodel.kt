package com.example.demoapp.catmodule.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.catmodule.domain.CatListRemoteUseCase
import com.example.demoapp.core.common.Status
import com.example.demoapp.core.network.ResponseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatListViewmodel @Inject constructor(val catListRemoteUseCase: CatListRemoteUseCase) : ViewModel() {


        val dataStateFlow = MutableStateFlow<ResponseApi>(ResponseApi.loading(Status.LOADING))

    init {

        viewModelScope.launch {
             val responseApi= catListRemoteUseCase.getCatData()

            dataStateFlow.value=responseApi
        }
    }




}