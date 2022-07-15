package com.marvelapp.data.mapper

import com.marvelapp.data.model.*
import com.marvelapp.domain.model.*

class MarvelCharacterItemResponseMapperImpl :MarvelCharacterItemResponseMapper {
    override fun toMarvelCharacterData(from: CharacterDataResponse) = MarvelCharacterData(
        result = from.results?.map { toMarvelCharacterItem(it) },
        total = from.total
    )

    override fun toMarvelCharacterItem(from: CharacterItemResponse)=CharacterItem(
        id = from.id,
        name = from.name ?: "",
        description = from.description ?: "",
        thumbnail = from.thumbnail?.let { toThumbnail(it) },
        comics = from.comics?.let { toMarvelComics(it) },
        series = from.series?.let { toMarvelSeries(it) },
        stories = from.stories?.let { toMarvelStories(it) },
        events = from.events?.let { toMarvelEvents(it) },
    )

    private fun toThumbnail(from: ThumbnailResponse) =Thumbnail(
        extension = from.extension ?: "",
        path = from.path ?: ""
    )

    private fun toMarvelComics(from: CommonResponse)= Comics(
        available = from.available ?: "",
        collectionURI = from.collectionURI,
        items = from.items.let { it -> it.map { toItem(it) } }
    )

    private fun toMarvelSeries(from: CommonResponse) = Series(
        available = from.available ?: "",
        collectionURI = from.collectionURI,
        items = from.items.let { it -> it.map { toItem(it) } }
    )

    private fun toMarvelEvents(from: CommonResponse) = Events(
        available = from.available ?: "",
        collectionURI = from.collectionURI,
        items = from.items.let { it -> it.map { toItem(it) } }
    )

    private fun toMarvelStories(from: CommonResponse)=  Stories(
        available = from.available ?: "",
        collectionURI = from.collectionURI,
        items = from.items.let { it -> it.map { toItem(it) } }
    )

    private fun toItem(from: ItemResponse) = Item(
        name = from.name ?: "",
        resourceURI = from.resourceUri ?: ""
    )
}