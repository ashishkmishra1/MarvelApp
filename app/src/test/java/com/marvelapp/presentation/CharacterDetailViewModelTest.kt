package com.marvelapp.presentation

import androidx.lifecycle.Observer
import com.marvelapp.domain.model.CharacterModel
import com.marvelapp.domain.model.ResourceItemModel
import com.marvelapp.domain.usecase.GetMarvelCharacterDetailsUseCase
import com.marvelapp.presentation.characterdetails.CharacterDetailViewModel
import com.marvelapp.testdata.TestData
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.confirmVerified
import io.mockk.mockk
import org.junit.Test

class CharacterDetailViewModelTest : BaseViewModelTest() {
    private val useCase = mockk<GetMarvelCharacterDetailsUseCase>()

    private val dataLiveDataObserver = mockk<Observer<CharacterModel>>(relaxed = true)
    private val comicsLiveDataObserver = mockk<Observer<List<ResourceItemModel>>>(relaxed = true)
    private val errorLiveDataObserver = mockk<Observer<Boolean>>(relaxed = true)
    private val loadingLiveDataObserver = mockk<Observer<Boolean>>(relaxed = true)


    private fun instantiateViewModel(): CharacterDetailViewModel {
        val viewModel = CharacterDetailViewModel(useCase)
        viewModel.data.observeForever(dataLiveDataObserver)
        viewModel.comics.observeForever(comicsLiveDataObserver)
        viewModel.error.observeForever(errorLiveDataObserver)
        viewModel.loading.observeForever(loadingLiveDataObserver)
        return viewModel
    }

    @Test
    fun `Given character id When fetch character details Then load data`() {
        val viewModel = instantiateViewModel()
        val id: Long = 123

        coEvery { useCase.invoke(id) } returns TestData.MARVEL_CHARACTER_ITEM

        viewModel.fetchCharacterDetails(id)

        coVerifyOrder {
            loadingLiveDataObserver.onChanged(true)
            dataLiveDataObserver.onChanged(TestData.MARVEL_CHARACTER_ITEM)
        }
        confirmVerified(
            dataLiveDataObserver,
            errorLiveDataObserver,
            loadingLiveDataObserver
        )
    }

    @Test
    fun `Given character id When fetch character details with error Then show error`() {
        val viewModel = instantiateViewModel()
        val id: Long = 123

        coEvery { useCase.invoke(id) } throws Exception()

        viewModel.fetchCharacterDetails(id)

        coVerifyOrder {
            loadingLiveDataObserver.onChanged(true)
            errorLiveDataObserver.onChanged(true)
        }
        confirmVerified(
            dataLiveDataObserver,
            errorLiveDataObserver,
            loadingLiveDataObserver
        )
    }

}