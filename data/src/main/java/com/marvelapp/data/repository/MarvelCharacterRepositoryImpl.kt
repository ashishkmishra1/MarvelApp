package com.marvelapp.data.repository

import com.marvelapp.data.datasource.Source
import com.marvelapp.data.mapper.MarvelCharacterItemResponseMapper
import com.marvelapp.domain.repository.MarvelCharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarvelCharacterRepositoryImpl(
    private val source: Source,
    private val marvelCharacterItemResponseMapper: MarvelCharacterItemResponseMapper,
) : MarvelCharacterRepository {

    override suspend fun getMarvelCharacters(
        limit: Int,
        offset: Int,
    ) = withContext(Dispatchers.IO) {
        marvelCharacterItemResponseMapper.toMarvelCharacterDetailsModel(
            source.fetchMarvelCharacter(
                limit,
                offset
            ).data
        )
    }

    override suspend fun getMarvelCharacterDetails(id: Long) = withContext(Dispatchers.IO) {
        val marvelDetails = source.fetchCharacterDetails(id)
        marvelDetails.data.results.firstOrNull()?.let {
            marvelCharacterItemResponseMapper.toMarvelCharacterModel(it)
        }
    }


}