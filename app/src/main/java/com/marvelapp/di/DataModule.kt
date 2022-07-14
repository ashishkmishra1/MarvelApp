package com.marvelapp.di

import com.marvelapp.data.mapper.MarvelCharacterItemResponseMapperImpl
import com.marvelapp.data.repository.MarvelCharacterRepositoryImpl
import com.marvelapp.domain.repository.MarvelCharacterRepository
import com.marvelapp.domain.usecase.GetMarvelCharacterDetailsUseCase
import com.marvelapp.domain.usecase.GetMarvelCharacterUseCase
import org.koin.dsl.module

val dataModule = module {
    factory<MarvelCharacterRepository> { MarvelCharacterRepositoryImpl(get(), get()) }
    single { GetMarvelCharacterUseCase(get()) }
    single { GetMarvelCharacterDetailsUseCase(get()) }
    single{MarvelCharacterItemResponseMapperImpl()}

}