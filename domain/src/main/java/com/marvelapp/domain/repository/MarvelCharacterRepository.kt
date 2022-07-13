package com.marvelapp.domain.repository

import com.marvelapp.domain.model.CharacterItem
import com.marvelapp.domain.model.MarvelCharacterData

interface MarvelCharacterRepository {
    suspend fun getMarvelCharacters(limit: Int, offset: Int): MarvelCharacterData
    suspend fun getMarvelCharacterDetails(id: Long): CharacterItem?
}