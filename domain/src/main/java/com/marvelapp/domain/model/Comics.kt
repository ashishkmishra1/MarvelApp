package com.marvelapp.domain.model


data class Comics(
    val available: String = "",
    val collectionURI: String = "",
    val items: List<Item>? = null,
)
