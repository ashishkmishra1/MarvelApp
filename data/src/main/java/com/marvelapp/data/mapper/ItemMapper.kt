package com.marvelapp.data.mapper

import com.marvelapp.data.model.ItemResponse
import com.marvelapp.domain.model.Item

class ItemMapper : DomainMapper<ItemResponse, Item> {
    override fun toDomain(from: ItemResponse) = Item(
        name = from.name ?: "",
        resourceURI = from.resourceUri ?: ""
    )

    override fun toDomain(from: List<ItemResponse>) = from.map { toDomain(it) }
}