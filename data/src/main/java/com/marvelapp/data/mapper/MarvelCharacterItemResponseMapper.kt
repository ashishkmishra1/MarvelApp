package com.marvelapp.data.mapper

import com.marvelapp.data.model.*
import com.marvelapp.domain.model.*

interface MarvelCharacterItemResponseMapper {
    fun toMarvelCharacterData(from: CharacterDataResponse): MarvelCharacterData
    fun toMarvelCharacterItem(from:CharacterItemResponse): CharacterItem
    fun toThumbnail(from: ThumbnailResponse) :Thumbnail
    fun toMarvelComics(from:CommonResponse): Comics
    fun toMarvelSeries(from:CommonResponse): Series
    fun toMarvelEvents(from:CommonResponse): Events
    fun toMarvelStories(from:CommonResponse): Stories
    fun toItem(from: ItemResponse) : Item



}