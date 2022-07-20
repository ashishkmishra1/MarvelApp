package com.marvelapp.domain.model

data class CharacterDetailsModel(
    val result: List<CharacterModel>,
    val total: Int
)