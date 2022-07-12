package com.marvelapp.data.mapper

import com.marvelapp.data.model.CommonResponse
import com.marvelapp.domain.model.Comics
import com.marvelapp.domain.model.Series
import com.marvelapp.domain.model.Stories

class SeriesMapper : DomainMapper<CommonResponse, Series> {
    override fun toDomain(from: CommonResponse) = Series(
        available = from.available ?: "",
        collectionURI = from.collectionURI,
        items = from.items.let { ItemMapper().toDomain(it) }
    )

    override fun toDomain(from: List<CommonResponse>) = from.map { toDomain(it) }
}