package com.marvelapp.domain.model


data class Events(
    val available: String = "",
    val collectionURI: String = "",
    val items: List<Item>? = null,
)