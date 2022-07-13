package com.marvelapp.presentation.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marvelapp.domain.model.CharacterItem
import com.marvelapp.domain.model.MarvelCharacterData
import com.marvelapp.domain.usecase.GetMarvelCharacterUseCase
import com.marvelapp.presentation.base.BaseViewModel


class MarvelCharacterViewModel(private val useCase: GetMarvelCharacterUseCase) : BaseViewModel() {

    companion object {
        private const val ITEM_COUNT_PER_PAGE = 20
    }

    private val emptyResultStatus: MutableLiveData<Boolean> = MutableLiveData()
    val emptyResult: MutableLiveData<Boolean> = emptyResultStatus

    private val marvelCharacters: MutableLiveData<List<CharacterItem>> = MutableLiveData()
    val character: LiveData<List<CharacterItem>> = marvelCharacters

    private var currentPageCounter = 0


    fun fetchMarvelCharacter() {
        launch {
            val response: MarvelCharacterData =
                useCase.invoke(ITEM_COUNT_PER_PAGE, currentPageCounter)
            marvelCharacters.postValue(response.result)
        }
    }

    fun fetchMarvelCharacterNextPage() {
        launch(true) {
            currentPageCounter += ITEM_COUNT_PER_PAGE

            val response: MarvelCharacterData =
                useCase.invoke(ITEM_COUNT_PER_PAGE, currentPageCounter)

            val shouldLoadMoreData = currentPageCounter / ITEM_COUNT_PER_PAGE < response.total

            if (shouldLoadMoreData) {
                marvelCharacters.value?.let {
                    marvelCharacters.postValue(it + response.result)
                } ?: marvelCharacters.postValue(response.result)
            }
        }
    }


}