package com.marvelapp.domain.testdata

import com.marvelapp.data.model.CharacterDataResponse
import com.marvelapp.data.model.CharacterItemResponse
import com.marvelapp.data.model.MarvelCharacterResponse
import com.marvelapp.domain.model.CharacterItem
import com.marvelapp.domain.model.MarvelCharacterData
object TestData {

    val MARVEL_CHARACTER_RESPONSE_LIST = MarvelCharacterResponse(
        CharacterDataResponse(
            listOf(
                CharacterItemResponse(
                    id = 1011334,
                    name = "3-D Man"
                )
            ), 1
        )
    )

    val MARVEL_CHARACTER_ITEM = CharacterItem(
        id = 1011334,
        name = "3-D Man"
    )

    val MARVEL_CHARACTER_DATA = MarvelCharacterData(
        result = listOf(MARVEL_CHARACTER_ITEM),
        total = 20
    )

}