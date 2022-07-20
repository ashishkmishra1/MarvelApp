package com.marvelapp.di

import com.marvelapp.data.datasource.Source
import com.marvelapp.data.datasource.RemoteDataSource
import com.marvelapp.data.datasource.RemoteDataSourceImpl
import com.marvelapp.data.mapper.MarvelCharacterItemResponseMapper
import com.marvelapp.data.mapper.MarvelCharacterItemResponseMapperImpl
import com.marvelapp.data.repository.MarvelCharacterRepositoryImpl
import com.marvelapp.domain.repository.MarvelCharacterRepository
import com.marvelapp.domain.usecase.GetMarvelCharacterDetailsUseCase
import com.marvelapp.domain.usecase.GetMarvelCharacterUseCase
import org.koin.dsl.module

val dataModule = module {
    factory<MarvelCharacterRepository> { MarvelCharacterRepositoryImpl(get(), get()) }
    single { MarvelCharacterItemResponseMapperImpl() as MarvelCharacterItemResponseMapper }
    single { GetMarvelCharacterDetailsUseCase(get()) }
    single { GetMarvelCharacterUseCase(get()) }
    single { RemoteDataSourceImpl(get()) as RemoteDataSource }
    single { Source(get()) }
}