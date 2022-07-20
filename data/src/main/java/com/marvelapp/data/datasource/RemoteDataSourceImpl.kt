package com.marvelapp.data.datasource

import com.marvelapp.data.remote.MarvelApi

class RemoteDataSourceImpl(private val marvelApi: MarvelApi) : RemoteDataSource {
    override suspend fun fetchMarvelCharacter(limit: Int, offset: Int) = marvelApi.getMarvelCharacters(limit,offset)

    override suspend fun fetchCharacterDetails(id: Long) = marvelApi.getMarvelCharacterDetails(id)
}