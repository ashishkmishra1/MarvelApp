package com.marvelapp.data.mapper

import com.marvelapp.data.model.CharacterDetailsEntity
import com.marvelapp.data.model.CharacterEntity
import com.marvelapp.domain.model.CharacterDetailsModel
import com.marvelapp.domain.model.CharacterModel

interface MarvelCharacterItemResponseMapper {
    fun toMarvelCharacterDetailsModel(from: CharacterDetailsEntity): CharacterDetailsModel
    fun toMarvelCharacterModel(from: CharacterEntity): CharacterModel
}