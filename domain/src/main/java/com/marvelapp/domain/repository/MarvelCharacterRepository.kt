package com.marvelapp.domain.repository

import com.marvelapp.domain.model.CharacterDetailsModel
import com.marvelapp.domain.model.CharacterModel

interface MarvelCharacterRepository {
    suspend fun getMarvelCharacters(limit: Int, offset: Int): CharacterDetailsModel
    suspend fun getMarvelCharacterDetails(id: Long): CharacterModel?
}