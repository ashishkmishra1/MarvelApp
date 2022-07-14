package com.marvelapp.presentation.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.marvelapp.domain.model.CharacterItem
import com.marvelapp.domain.model.Item
import com.marvelapp.domain.usecase.GetMarvelCharacterDetailsUseCase
import com.marvelapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class CharacterDetailViewModel(val useCase: GetMarvelCharacterDetailsUseCase) : BaseViewModel() {
    private val characterItemMLD: MutableLiveData<CharacterItem> = MutableLiveData()
    val data: LiveData<CharacterItem> = characterItemMLD

    private val comicsItemList: MutableLiveData<List<Item>> = MutableLiveData()
    val comics: LiveData<List<Item>> = comicsItemList

    private val characterName: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String> = characterName

    private val characterImage: MutableLiveData<String> = MutableLiveData()
    val image: LiveData<String> = characterImage

    private val characterDescription: MutableLiveData<String> = MutableLiveData()
    val description: LiveData<String> = characterDescription

    private val characterSeriesCount: MutableLiveData<Int> = MutableLiveData()
    val seriesCount: LiveData<Int> = characterSeriesCount

    private val characterComicsCount: MutableLiveData<Int> = MutableLiveData()
    val comicsCount: LiveData<Int> = characterComicsCount

    private val characterEventCount: MutableLiveData<Int> = MutableLiveData()
    val eventCount: LiveData<Int> = characterEventCount

    private val characterStoriesCount: MutableLiveData<Int> = MutableLiveData()
    val storiesCount: LiveData<Int> = characterStoriesCount

    fun fetchCharacterDetails(id: Long) {
        viewModelScope.launch {
            val response = useCase.invoke(id)
            response?.let { characterItemMLD.postValue(it) }
            updateCharacterData(response)
        }
    }

    private fun updateCharacterData(characterItem: CharacterItem?) {
        characterItem?.let {
            it.comics?.items?.let {
                comicsItemList.value = it
            }
            characterImage.value = it.getImageUrl()
            characterDescription.value = it.description
            characterName.value = it.name
            characterStoriesCount.value = it.stories?.items?.size ?: 0
            characterEventCount.value = it.events?.items?.size ?: 0
            characterSeriesCount.value = it.series?.items?.size ?: 0
            characterComicsCount.value = it.comics?.items?.size ?: 0
        }
    }

}