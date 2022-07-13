package com.marvelapp.domain.model


data class Series(
    val available: String = "",
    val collectionURI: String = "",
    val items: List<Item>? = null,
)
