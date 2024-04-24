package com.example.demoapp.catmodule.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.catmodule.data.model.CatResModel
import com.example.demoapp.catmodule.domain.CatListRemoteUseCase
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

    private val _catListState = MutableStateFlow<ResponseApi>(ResponseApi.loading(Status.CATDETAILS))
    val catListState: StateFlow<ResponseApi> = _catListState
    init {
        getCatData()


    }


    fun getCatData()
    {
        viewModelScope.launch(Dispatchers.IO) {
            val result:ResponseApi = catListRemoteUseCase.getCatData()

            withContext(Dispatchers.Main){
                _catListState.value = when (result.status)
                {
                    Status.SUCCESS->
                    {
                        ResponseApi.success(result.data as List<CatResModel>,Status.CATDETAILS)
                    }
                    Status.AUTH_FAIL->
                    {
                        ResponseApi.authFail(Status.CATDETAILS)
                    }
                    else->
                    {
                        ResponseApi.fail(result.data,Status.CATDETAILS)
                    }

                }
            }

        }

    }


}