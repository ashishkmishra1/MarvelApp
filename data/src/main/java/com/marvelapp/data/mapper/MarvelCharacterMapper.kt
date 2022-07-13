package com.marvelapp.data.mapper

import com.marvelapp.data.model.CharacterDataResponse
import com.marvelapp.domain.model.MarvelCharacterData

class MarvelCharacterMapper : DomainMapper<CharacterDataResponse, MarvelCharacterData> {
    override fun toDomain(from: CharacterDataResponse) = MarvelCharacterData(
        result = MarvelCharacterItemMapper().toDomain(from.results),
        total = from.total
    )

    override fun toDomain(from: List<CharacterDataResponse>) = from.map { toDomain(it) }
}