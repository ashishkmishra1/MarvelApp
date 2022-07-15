package com.marvelapp.data.mapper

import com.marvelapp.data.model.*
import com.marvelapp.domain.model.*

interface MarvelCharacterItemResponseMapper {
    fun toMarvelCharacterData(from: CharacterDataResponse): MarvelCharacterData
    fun toMarvelCharacterItem(from:CharacterItemResponse): CharacterItem
}