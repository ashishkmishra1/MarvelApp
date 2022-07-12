package com.marvelapp.data.model

import com.marvelapp.domain.model.Events
import com.marvelapp.domain.model.Stories

data class MarvelCharacterResponse(
    val data: CharacterDataResponse
)

data class CharacterDataResponse(
    val results: List<CharacterItemResponse>,
    val total: Int
)

data class CharacterItemResponse(
    val id: Long,
    val name: String?,
    val description: String?,
    val isFavourite: Boolean?,
    val thumbnail: ThumbnailResponse,
    val comics: CommonResponse?,
    val series: CommonResponse?,
    val stories: CommonResponse?,
    val events: CommonResponse
)

data class CommonResponse(
    val available: String?,
    val collectionURI: String,
    val items: List<ItemResponse>
)


data class ItemResponse(
    val name: String?,
    val resourceUri: String?,
    val type: String?
)

data class ThumbnailResponse(
    val extension: String?,
    val path: String?
)