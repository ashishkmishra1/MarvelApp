package com.marvelapp.data.mapper

import com.marvelapp.data.model.CommonResponse
import com.marvelapp.domain.model.Comics

class ComicsMapper : DomainMapper<CommonResponse, Comics> {
    override fun toDomain(from: CommonResponse) = Comics(
        available = from.available ?: "",
        collectionURI = from.collectionURI,
        items = from.items.let { ItemMapper().toDomain(it) }
    )

    override fun toDomain(from: List<CommonResponse>) = from.map { toDomain(it) }
}