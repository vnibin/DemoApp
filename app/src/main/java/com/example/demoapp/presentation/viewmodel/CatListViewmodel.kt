package com.example.demoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.domain.usecase.CatListRemoteUseCase
import com.example.demoapp.core.common.Status
import com.example.demoapp.core.network.ResponseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CatListViewmodel @Inject constructor(val catListRemoteUseCase: CatListRemoteUseCase) : ViewModel() {

    private val _catListState = MutableStateFlow(ResponseApi.loading(Status.CATLIST))
    val catListState: StateFlow<ResponseApi> = _catListState
    init {
        getCatData()
    }


    fun getCatData()
    {
        viewModelScope.launch(Dispatchers.IO) {
            val result: ResponseApi = catListRemoteUseCase.getCatData()

            withContext(Dispatchers.Main){
                _catListState.value = result

            }

        }

    }


}