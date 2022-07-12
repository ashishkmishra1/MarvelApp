package com.marvelapp.presentation.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marvelapp.domain.model.CharacterItem
import com.marvelapp.domain.model.Item
import com.marvelapp.domain.usecases.GetMarvelCharacterDetailsUseCase
import com.marvelapp.presentation.base.BaseViewModel

class CharacterDetailViewModel(val useCase: GetMarvelCharacterDetailsUseCase) : BaseViewModel() {
    private val _data: MutableLiveData<CharacterItem> = MutableLiveData()
    val data: LiveData<CharacterItem> = _data

    private val _comics: MutableLiveData<List<Item>> = MutableLiveData()
    val comics: LiveData<List<Item>> = _comics

    fun fetchCharacterDetails(id: Long) {
        launch {
            _data.postValue(useCase.invoke(id))
        }
        _data.value?.comics?.items?.let {
            _comics.value = it

        }
    }



}