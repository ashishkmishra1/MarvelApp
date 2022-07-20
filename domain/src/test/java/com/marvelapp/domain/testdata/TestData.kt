package com.marvelapp.domain.testdata

import com.marvelapp.data.model.CharacterDetailsEntity
import com.marvelapp.data.model.CharacterEntity
import com.marvelapp.data.model.MarvelCharacterEntity
import com.marvelapp.domain.model.CharacterModel
import com.marvelapp.domain.model.CharacterDetailsModel
object TestData {

    val MARVEL_CHARACTER_RESPONSE_LIST = MarvelCharacterEntity(
        CharacterDetailsEntity(
            listOf(
                CharacterEntity(
                    id = 1011334,
                    name = "3-D Man"
                )
            ), 1
        )
    )

    val MARVEL_CHARACTER_ITEM = CharacterModel(
        id = 1011334,
        name = "3-D Man",
        "",
        null,
        null,
        null,
        null,
        null
    )

    val MARVEL_CHARACTER_DATA = CharacterDetailsModel(
        result = listOf(MARVEL_CHARACTER_ITEM),
        total = 20
    )

}