package com.marvelapp.data.datasource

import com.marvelapp.data.model.MarvelCharacterEntity

interface RemoteDataSource {
    suspend fun fetchMarvelCharacter(limit: Int, offset: Int): MarvelCharacterEntity
    suspend fun fetchCharacterDetails(id: Long): MarvelCharacterEntity

}