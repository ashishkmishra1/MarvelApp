package com.marvelapp.data.mapper

import com.marvelapp.data.model.*
import com.marvelapp.domain.model.*

class MarvelCharacterItemResponseMapperImpl : MarvelCharacterItemResponseMapper {
    override fun toMarvelCharacterDetailsModel(from: CharacterDetailsEntity) =
        CharacterDetailsModel(
            result = from.results.map { toMarvelCharacterModel(it) },
            total = from.total ?: 0
        )

    override fun toMarvelCharacterModel(from: CharacterEntity) = CharacterModel(
        id = from.id ?: 0,
        name = from.name ?: "",
        description = from.description ?: "",
        thumbnailModel = from.thumbnail?.let { toThumbnailModel(it) },
        comicsModel = from.comics?.let { toCommonModel(it) },
        seriesModel = from.series?.let { toCommonModel(it) },
        storiesModel = from.stories?.let { toCommonModel(it) },
        events = from.events?.let { toCommonModel(it) },
    )

    private fun toThumbnailModel(from: ThumbnailEntity) = ThumbnailModel(
        extension = from.extension ?: "",
        path = from.path ?: ""
    )

    private fun toCommonModel(from: CommonEntity) = CommonModel(
        available = from.available ?: "",
        collectionURI = from.collectionURI ?: "",
        items = from.items.let {
            it?.map { resourceItemEntity ->
                toResourceItemModel(
                    resourceItemEntity
                )
            }
        }
    )

    private fun toResourceItemModel(from: ResourceItemEntity) = ResourceItemModel(
        name = from.name ?: "",
        resourceURI = from.resourceUri ?: ""
    )
}