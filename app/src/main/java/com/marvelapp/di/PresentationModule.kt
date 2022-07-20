package com.marvelapp.di

import com.marvelapp.presentation.character.MarvelCharacterViewModel
import com.marvelapp.presentation.characterdetails.CharacterDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MarvelCharacterViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }
}