package com.marvelapp.presentation.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marvelapp.domain.model.CharacterItem
import com.marvelapp.domain.model.MarvelCharacterData
import com.marvelapp.domain.usecases.GetMarvelCharacterUseCase
import com.marvelapp.presentation.base.BaseViewModel


const val ITEM_COUNT_PER_PAGE = 20
const val INITIAL_PAGE_COUNTER = 0
const val MIN_SIZE_SEARCH = 5

class MarvelCharacterViewModel(private val useCase: GetMarvelCharacterUseCase) : BaseViewModel() {


    private val _emptyResult: MutableLiveData<Boolean> = MutableLiveData()
    val emptyResult: MutableLiveData<Boolean> = _emptyResult

    private val _marvelCharacters: MutableLiveData<List<CharacterItem>> = MutableLiveData()
    val character: LiveData<List<CharacterItem>> = _marvelCharacters

    private var currentPageCounter = 0


    fun fetchMarvelCharacter() {
        launch {
            val response: MarvelCharacterData =
                useCase.invoke(ITEM_COUNT_PER_PAGE, currentPageCounter)
            _marvelCharacters.postValue(response.result)
        }
    }

    fun fetchMarvelCharacterNextPage() {
        launch(true) {
            currentPageCounter += ITEM_COUNT_PER_PAGE

            val response: MarvelCharacterData =
                useCase.invoke(ITEM_COUNT_PER_PAGE, currentPageCounter)

            val shouldLoadMoreData = currentPageCounter / ITEM_COUNT_PER_PAGE < response.total

            if (shouldLoadMoreData) {
                _marvelCharacters.value?.let {
                    _marvelCharacters.postValue(it + response.result)
                } ?: _marvelCharacters.postValue(response.result)
            }
        }
    }


}