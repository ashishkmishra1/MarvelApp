package com.marvelapp.presentation

import androidx.lifecycle.Observer
import com.marvelapp.domain.model.CharacterItem
import com.marvelapp.domain.usecase.GetMarvelCharacterUseCase
import com.marvelapp.presentation.character.MarvelCharacterViewModel
import com.marvelapp.testdata.TestData.MARVEL_CHARACTER_DATA
import com.marvelapp.testdata.TestData.MARVEL_CHARACTER_ITEM
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.confirmVerified
import io.mockk.mockk
import org.junit.Test

class CharacterViewModelTest : BaseViewModelTest() {
    private val useCase = mockk<GetMarvelCharacterUseCase>()

    private val marvelCharacterLiveDataObserver =
        mockk<Observer<List<CharacterItem>>>(relaxed = true)
    private val errorLiveDataObserver = mockk<Observer<Boolean>>(relaxed = true)
    private val loadingLiveDataObserver = mockk<Observer<Boolean>>(relaxed = true)
    private val emptyResultLiveDataObserver = mockk<Observer<Boolean>>(relaxed = true)


    private fun instantiateViewModel(): MarvelCharacterViewModel {
        val viewModel = MarvelCharacterViewModel(useCase)
        viewModel.character.observeForever(marvelCharacterLiveDataObserver)
        viewModel.error.observeForever(errorLiveDataObserver)
        viewModel.loading.observeForever(loadingLiveDataObserver)
        viewModel.emptyResult.observeForever(emptyResultLiveDataObserver)
        return viewModel
    }

    @Test
    fun `given first page load when fetch characters then return list `() {
        val viewModel = instantiateViewModel()

        coEvery { useCase.invoke(20, 0) } returns MARVEL_CHARACTER_DATA

        viewModel.fetchMarvelCharacter()

        coVerifyOrder {
            loadingLiveDataObserver.onChanged(true)
            marvelCharacterLiveDataObserver.onChanged(listOf(MARVEL_CHARACTER_ITEM))
        }
        confirmVerified(
            marvelCharacterLiveDataObserver,
            errorLiveDataObserver,
            loadingLiveDataObserver
        )
    }


    @Test
    fun `given first page load when fetch characters with error then show error `() {
        val viewModel = instantiateViewModel()

        coEvery { useCase.invoke(20, 0) } throws Exception()

        viewModel.fetchMarvelCharacter()

        coVerifyOrder {
            loadingLiveDataObserver.onChanged(true)
            errorLiveDataObserver.onChanged(true)
        }
        confirmVerified(
            marvelCharacterLiveDataObserver,
            errorLiveDataObserver,
            loadingLiveDataObserver
        )
    }

    @Test
    fun `given second page load when fetch characters then return list `() {
        val viewModel = instantiateViewModel()

        coEvery { useCase.invoke(20, 20) } returns MARVEL_CHARACTER_DATA

        viewModel.fetchMarvelCharacterNextPage()

        coVerifyOrder {
            marvelCharacterLiveDataObserver.onChanged(listOf(MARVEL_CHARACTER_ITEM))
        }
        confirmVerified(
            marvelCharacterLiveDataObserver,
            errorLiveDataObserver,
            loadingLiveDataObserver
        )
    }


}