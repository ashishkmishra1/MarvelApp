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

    private val comicsItemListMLD: MutableLiveData<List<Item>> = MutableLiveData()
    val comics: LiveData<List<Item>> = comicsItemListMLD

    private val characterNameMLD: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String> = characterNameMLD

    private val characterImageMLD: MutableLiveData<String> = MutableLiveData()
    val image: LiveData<String> = characterImageMLD

    private val characterDescriptionMLD: MutableLiveData<String> = MutableLiveData()
    val description: LiveData<String> = characterDescriptionMLD

    private val seriesCountMLD: MutableLiveData<Int> = MutableLiveData()
    val seriesCount: LiveData<Int> = seriesCountMLD

    private val comicsCountMLD: MutableLiveData<Int> = MutableLiveData()
    val comicsCount: LiveData<Int> = comicsCountMLD

    private val eventCountMLD: MutableLiveData<Int> = MutableLiveData()
    val eventCount: LiveData<Int> = eventCountMLD

    private val storiesCountMLD: MutableLiveData<Int> = MutableLiveData()
    val storiesCount: LiveData<Int> = storiesCountMLD


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
                comicsItemListMLD.value = it
            }
            characterImageMLD.value = it.getImageUrl()
            characterDescriptionMLD.value = it.description
            characterNameMLD.value = it.name
            storiesCountMLD.value = it.stories?.items?.size ?: 0
            eventCountMLD.value = it.events?.items?.size ?: 0
            seriesCountMLD.value = it.series?.items?.size ?: 0
            comicsCountMLD.value = it.comics?.items?.size ?: 0

        }
    }

}