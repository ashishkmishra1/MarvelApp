package com.marvelapp.data.repository

import com.marvelapp.data.mapper.MarvelCharacterItemResponseMapperImpl
import com.marvelapp.data.remote.MarvelApi
import com.marvelapp.domain.repository.MarvelCharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarvelCharacterRepositoryImpl(
    private val api: MarvelApi,
    private val marvelCharacterItemResponseMapperImpl: MarvelCharacterItemResponseMapperImpl,
) : MarvelCharacterRepository {

    override suspend fun getMarvelCharacters(
        limit: Int,
        offset: Int,
    ) = withContext(Dispatchers.IO) {
        marvelCharacterItemResponseMapperImpl.toMarvelCharacterData(api.getMarvelCharacters(limit, offset).data)
    }


    override suspend fun getMarvelCharacterDetails(id: Long) = withContext(Dispatchers.IO) {
        val marvelDetails = api.getMarvelCharacterDetails(id)
        marvelDetails.data.results.firstOrNull()?.let {
            marvelCharacterItemResponseMapperImpl.toMarvelCharacterItem(it)
        }
    }


}