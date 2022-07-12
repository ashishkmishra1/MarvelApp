package com.marvelapp.data.repository

import com.marvelapp.data.remote.MarvelApi
import com.marvelapp.data.mapper.MarvelCharacterItemMapper
import com.marvelapp.data.mapper.MarvelCharacterMapper
import com.marvelapp.domain.repository.MarvelCharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarvelCharacterRepositoryImpl(
    private val api: MarvelApi,
    private val marvelCharacterMapper: MarvelCharacterMapper,
    private val marvelCharacterItemMapper: MarvelCharacterItemMapper
) : MarvelCharacterRepository {

    override suspend fun getMarvelCharacters(
        limit: Int,
        offset: Int,
    ) = withContext(Dispatchers.IO) {
        marvelCharacterMapper.toDomain(api.getMarvelCharacters(limit, offset).data)
    }


    override suspend fun getMarvelCharacterDetails(id: Long) = withContext(Dispatchers.IO) {
        val marvelDetails = api.getMarvelCharacterDetails(id)
        marvelDetails.data.results.firstOrNull()?.let {
            marvelCharacterItemMapper.toDomain(it)
        }
    }


}