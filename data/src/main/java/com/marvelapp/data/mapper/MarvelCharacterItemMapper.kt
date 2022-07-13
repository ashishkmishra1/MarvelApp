package com.marvelapp.data.mapper

import com.marvelapp.data.model.CharacterItemResponse
import com.marvelapp.domain.model.CharacterItem

class MarvelCharacterItemMapper : DomainMapper<CharacterItemResponse, CharacterItem> {
    override fun toDomain(from: CharacterItemResponse) = CharacterItem(
        id = from.id,
        name = from.name ?: "",
        description = from.description ?: "",
        thumbnail = from.thumbnail?.let { ThumbnailMapper().toDomain(it) },
        comics = from.comics?.let { ComicsMapper().toDomain(it) },
        series = from.series?.let { SeriesMapper().toDomain(it) },
        stories = from.stories?.let { StoriesMapper().toDomain(it) },
        events = from.events?.let { EventMapper().toDomain(it) },
    )

    override fun toDomain(from: List<CharacterItemResponse>) = from.map { toDomain(it) }
}