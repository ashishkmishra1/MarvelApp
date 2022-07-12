package com.marvelapp.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

abstract class BaseViewModel : ViewModel() {
    protected val _loadingStatus = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loadingStatus

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _isDataAvailable = MutableLiveData<Boolean>()
    val emptyData: LiveData<Boolean> = _isDataAvailable

    fun launch(disableLoading: Boolean = false, block: suspend () -> Unit) {
        if (disableLoading.not()) _loadingStatus.postValue(true)

        viewModelScope.launch {
            try {
                block()
            } catch (ex: Exception) {
                _error.postValue(true)
            }
        }
    }
}