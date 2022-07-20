package com.marvelapp.domain.model

data class CharacterModel(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnailModel: ThumbnailModel?,
    val comicsModel: CommonModel?,
    val seriesModel: CommonModel?,
    val events: CommonModel?,
    val storiesModel: CommonModel?
) {
    fun getImageUrl() = "${thumbnailModel?.path}.${thumbnailModel?.extension}"
}
