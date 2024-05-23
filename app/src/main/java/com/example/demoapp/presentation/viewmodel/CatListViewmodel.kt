package com.example.demoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.core.common.Resource
import com.example.demoapp.domain.usecase.CatListUseCase
import com.example.demoapp.presentation.state.CatListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CatListViewmodel @Inject constructor(private val catListUseCase: CatListUseCase) : ViewModel() {

    private val _catListState = MutableStateFlow(CatListState())
    val catListState: StateFlow<CatListState>
        get() = _catListState

    init {
        getCatData()
    }

    private fun getCatData() {


        catListUseCase().onEach {
            when (it) {
                is Resource.Error -> {
                    _catListState.value = CatListState().copy(errorMsg = it.msg)
                }
                is Resource.Loading -> {
                    _catListState.value = CatListState().copy(isLoading = true)
                }
                is Resource.Success -> {
                    _catListState.value = CatListState().copy(catList = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }


   /* fun getCatData()
    {

        viewModelScope.launch(Dispatchers.IO) {
            val result: ResponseApi = catListRemoteUseCase.getCatData()

            withContext(Dispatchers.Main){
                _catListState.value = result

            }

        }

    }
*/

}