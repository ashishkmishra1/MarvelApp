package com.marvelapp.domain.model


data class CommonModel(
    val available: String,
    val collectionURI: String,
    val items: List<ResourceItemModel>?,
)
