package com.marvelapp.data.mapper

import com.marvelapp.data.model.ThumbnailResponse
import com.marvelapp.domain.model.Thumbnail

class ThumbnailMapper : DomainMapper<ThumbnailResponse, Thumbnail> {
    override fun toDomain(from: ThumbnailResponse) = Thumbnail(
        extension = from.extension ?: "",
        path = from.path ?: ""
    )

    override fun toDomain(from: List<ThumbnailResponse>) = from.map { toDomain(it) }
}