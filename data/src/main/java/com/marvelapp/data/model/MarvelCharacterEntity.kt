package com.marvelapp.data.model

data class MarvelCharacterEntity(
    val data: CharacterDetailsEntity
)

data class CharacterDetailsEntity(
    val results: List<CharacterEntity>,
    val total: Int?
)

data class CharacterEntity(
    val id: Long?,
    val name: String?,
    val description: String? = null,
    val thumbnail: ThumbnailEntity? = null,
    val comics: CommonEntity? = null,
    val series: CommonEntity? = null,
    val stories: CommonEntity? = null,
    val events: CommonEntity? = null
)

data class CommonEntity(
    val available: String?,
    val collectionURI: String?,
    val items: List<ResourceItemEntity>?
)

data class ResourceItemEntity(
    val name: String?,
    val resourceUri: String?,
    val type: String?
)

data class ThumbnailEntity(
    val extension: String?,
    val path: String?
)