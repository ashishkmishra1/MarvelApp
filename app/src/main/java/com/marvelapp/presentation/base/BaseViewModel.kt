package com.marvelapp.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val loadingStatus = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = loadingStatus

    private val errorStatus = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = errorStatus

    fun launch(disableLoading: Boolean = false, block: suspend () -> Unit) {
        if (disableLoading.not()) loadingStatus.postValue(true)

        viewModelScope.launch {
            try {
                block()
            } catch (ex: Exception) {
                errorStatus.postValue(true)
            }
        }
    }
}