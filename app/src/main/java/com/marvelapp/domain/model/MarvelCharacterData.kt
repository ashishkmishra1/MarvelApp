package com.marvelapp.domain.model

data class MarvelCharacterData(
    val result: List<CharacterItem>,
    val total: Int
)