package com.marvelapp.data.mapper

import com.marvelapp.data.model.CommonResponse
import com.marvelapp.domain.model.Stories

class StoriesMapper : DomainMapper<CommonResponse, Stories> {
    override fun toDomain(from: CommonResponse) = Stories(
        available = from.available ?: "",
        collectionURI = from.collectionURI,
        items = from.items.let { ItemMapper().toDomain(it) }
    )

    override fun toDomain(from: List<CommonResponse>) = from.map { toDomain(it) }
}