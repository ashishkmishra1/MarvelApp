package com.marvelapp.domain.model

data class CharacterItem(
    val id: Long,
    val name: String = "",
    val description: String = "",
    val thumbnail: Thumbnail? = null,
    val comics: Comics? = null,
    val series: Series? = null,
    val events: Events? = null,
    val stories: Stories? = null
) {
    fun getImageUrl() = "${thumbnail?.path}.${thumbnail?.extension}"
}
